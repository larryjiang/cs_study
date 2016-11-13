;;exercise 2.21
(define (square-list items) 
  (define (iter things answer) 
    (if (null? things)
	answer
	(iter (cdr things) (cons (square (car things)) answer))))
  (iter items '()))
;;exercise 2.23
(define (j-for-each f items)
  (if (null? items)
      #t
      (begin (f (car items)) (j-for-each f (cdr items)))))
