mkdir d01
touch d01/f.txt
rsync d01 d02 # no effect
mkdir d03
rsync d01 d03 # no effect
rsync d01/ d04 # no effect
rsync -av d01 d05 # creates d01/ inside 
  ##> d05/d01/f.txt
rsync -av d01/ d06 # copies d01/*
  ##> d06/f.txt
mkdir d07
rsync -av d01 d07 # creates d01/ inside
mkdir d08
rsync -av d01/ d08 # copies d01/*
