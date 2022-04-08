\ls | bb -i '(filter #(-> % io/file .isDirectory) *input*)'

\ls | bb -i '(take 2 *input*)'

bb '(vec (dedupe *input*))' <<< '[1 1 1 1 2]'
