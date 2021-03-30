;;; elisp.el --- Description -*- lexical-binding: t; -*-
(provide 'elisp)
;;; elisp.el ends here

; Code from: [Learn elisp in Y Minutes](https://learnxinyminutes.com/docs/elisp/)
;
; eval-last-sexp
; eval-print-last-sexp
; eros-eval-last-sexp C-x C-e
(+ 1 2)
;; 3

(setq my-name "ali")

(insert "hello!")
;; hello!

(insert "hello " "world")
;; hello world

(insert "hello, " my-name)
;; hello, ali

(defun hello () (insert "hello " my-name))
;;

(hello)
;; hello ali

(defun hello (name) (insert "hello " name))

(hello "you")
;; hello you

(switch-to-buffer-other-window "*test*")

(progn
  (switch-to-buffer-other-window "*test*")
  (hello "you"))

(progn
  (switch-to-buffer-other-window "*test*")
  (erase-buffer)
  (hello "you"))

(progn
  (switch-to-buffer-other-window "*test*")
  (erase-buffer)
  (hello "you3")
  (other-window 1))

(let ((local-name "you4"))
  (switch-to-buffer-other-window "*test*")
  (erase-buffer)
  (hello local-name)
  (other-window 1))

(format "hello %s!/n" "visitor")
;; "hello visitor!/n"

(defun hello (name)
  (insert (format "hello %s" name)))
(hello "you")
;; hello you
