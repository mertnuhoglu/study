{ vals[$1][ARGIND] = $2 }
END {
    for (key in vals) {
        printf "%s", key
        for (fileNr=1; fileNr<=ARGIND; fileNr++) {
            printf "\t%d", vals[key][fileNr]
        }
        print ""
    }
}
