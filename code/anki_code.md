
## lftp 01

··  `` lftp ftp://ftp1.bizqualify.com -u {{c1::<user>,<pass>}} {{c1::-e}} "cd /home/bloomberg/; ls $file_ind; bye;" `` <br>

%

%

clozeq

---

## lftp 02: Fatal error: Certificate verification: Not trusted

Turn off SSL certificate verify:

Edit `~/.lftp/rc` or `/etc/lftp.conf`

··  `` set ssl:verify-certificate {{c1::no}} `` <br>

%

%

clozeq

---

## ssh 01: pass password

··  `` {{c1::sshpass}} {{c2::-p bizqualify1}} ssh root@ftp1.bizqualify.com `` <br>

%

%

clozeq

---

## gdrive01: download

    googledrive::{{c1::drive_download}}(googledrive::as_id("..."), type = "txt", path = "...", overwrite = T)

%

%

clozeq

---

