awk 'substr($0,0,24) ~ $1 { f=$1 }{ $0=f substr($0, length(f)+1) } 1' input01.txt
