# [[mert_utils_text_bbl.py]]
#
# prn: [[mert_utils.py]]
# exmp: [[20260310_exmp_bbl_parse.py]]
# spcs: 20260310-Translate--bbl-functions-from-lua-to-python   || ((c760fd82-e5fd-4af4-8c66-6c29dc12ddc7))
# [grok](https://grok.com/share/bGVnYWN5_10a750bd-2d38-4e43-922d-623e8bc318e1)

import re
from typing import Dict, List, Optional, Any


def bbl_uuid(line: Optional[str] = None) -> str:
    """
    Extract UUID from a line using the same priority as the Lua version:
    1. ((uuid)) block reference
    2. id:: uuid
    3. loose UUID anywhere
    """
    if not line:
        return ""

    # Style 1: ((uuid))
    m = re.search(r"\(\(([0-9a-fA-F-]{36})\)\)", line)
    if m:
        uuid = m.group(1).lower()
        if len(uuid) == 36 and re.match(r"^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", uuid):
            return uuid

    # Style 2: id:: uuid  (with optional whitespace)
    m = re.search(r"id\s*::\s*([0-9a-fA-F-]{36})", line)
    if m:
        uuid = m.group(1).lower()
        if len(uuid) == 36 and re.match(r"^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$", uuid):
            return uuid

    # Style 3: loose UUID anywhere
    m = re.search(r"([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})", line)
    if m:
        return m.group(1).lower()

    return ""


def bbl2tbl(input_str: str) -> Dict[str, Any]:
    """
    Naive title + tags splitter — splits on the first #, treats rest as tags.
    Very similar to the simple Lua version.
    """
    input_str = input_str.strip()

    # Find position of first #
    m = re.match(r"^(.*?)\s*#", input_str)
    if not m:
        # no tags
        return {"title": input_str.strip(), "tags": []}

    title_part = m.group(1).strip()
    tags_part = input_str[len(title_part):].strip()

    tags = []
    for tag in re.findall(r"#([^\s#]+)", tags_part):
        tags.append(tag)

    return {"title": title_part, "tags": tags}


def bbl_title(line: str, opts: Optional[Dict[str, bool]] = None) -> str:
    """
    Clean block title — removes ids, list markers, headers, urls, etc.
    When opts["tag"] == True → collects tags and appends them as `tag1 tag2`
    """
    opts = opts or {}
    l = line

    # Remove file protocol urls
    l = re.sub(r"\s*<url:file[^>]+>", "", l)

    # Remove leading ## or # 
    l = re.sub(r"^##+\s*", "", l)
    l = re.sub(r"^#+\s+", "", l)

    # Some normalizations
    l = re.sub(r"#f/log\s+", "f/log ", l)
    l = re.sub(r"\sTODO", " #TODO", l)

    if opts.get("tag", False):
        tags = []
        def tag_replacer(m):
            tags.append(m.group(1))
            return ""
        l = re.sub(r"#([^\s:]+)(?=[ :]|$)", tag_replacer, l)
        l = re.sub(r"\s+$", "", l)

        if tags:
            l = l.rstrip() + " `" + " ".join(tags) + "`"
    else:
        # remove all tags
        l = re.sub(r"#[\w][\w/:,-]*:?", "", l)

    # Remove block refs
    l = re.sub(r"\(\([^)]+\)\)\s*", "", l)

    # Remove list markers
    l = re.sub(r"^[\s*+–—-]+\s+", "", l)

    # Remove trailing ||
    l = re.sub(r"\s*\|\|\s*", " ", l)

    # Remove leading (
    l = re.sub(r"^\s*\(", "", l)

    # Normalize spaces
    l = re.sub(r"\s+", " ", l)
    l = l.strip()

    return l


def bbl2title(line: str, opts: Optional[Dict[str, bool]] = None) -> str:
    """Clean title + remove id=... and [text](url) parts (old name compatibility)"""
    cleaned = bbl_title(line, opts)
    cleaned = re.sub(r"\s*id=g\d+\s*$", "", cleaned, flags=re.IGNORECASE)
    cleaned = re.sub(r"\[([^]]+)\]\([^)]+\)", r"\1", cleaned)
    return cleaned.strip()

def bbl2title_w_url(line: str, opts: Optional[Dict[str, bool]] = None) -> str:
    """Clean title + remove id=... and [text](url) parts (old name compatibility)"""
    cleaned = bbl_title(line, opts)
    cleaned = re.sub(r"\s*id=g\d+\s*$", "", cleaned, flags=re.IGNORECASE)
    return cleaned.strip()


def bbl2bldt(ln: str, nxln: str) -> Dict[str, Any]:
    """
    Main high-level function: line + next line (usually id line) → bldt dict
    """
    dc = bbl2tbl(ln)
    uuid = bbl_uuid(nxln)
    dc["id"] = uuid

    # Find first tag that starts with s/
    status = None
    for tag in dc.get("tags", []):
        if tag.startswith("s/"):
            status = tag
            break
    dc["status"] = status

    return dc


def bldt2edn(bldt: Dict[str, Any]) -> str:
    """
    Convert bldt dict → EDN-like string
    (very strict format to match your Lua output)
    """
    tags = bldt.get("tags", [])
    tag_list = [f'"{t}"' for t in tags]
    tags_str = "[" + " ".join(tag_list) + "]"

    title = bldt.get("title", "")
    if title is None:
        title = ""

    return f'{{:id "{bldt.get("id", "")}", :tags {tags_str}, :title "{title}"}}'


def bbl2edn(ln: str, nxln: str) -> str:
    """One-step: line + id line → EDN string"""
    bldt = bbl2bldt(ln, nxln)
    return bldt2edn(bldt)


# --------------------------------------------------------------------
#  Aliases / deprecated names (for easier porting of old code)
# --------------------------------------------------------------------
logseq_uuid_extract = bbl_uuid
logseq_block_title_extract_pure = bbl_title
logseq_line_extract_without_id_string = bbl2title
block_to_edn = bldt2edn
