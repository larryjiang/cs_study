(define (stream-ref s n)
  (if (= n 0)
      (stream-car s)
      (stream-ref (stream-cdr s) (- n 1))))

(define (stream-map proc s)
  (if (stream-null? s)
      the-empty-stream
      (cons-stream (proc (stream-car s))
		   (stream-map proc (stream-cdr s)))))

(define (stream-for-each proc s)
  (if (stream-null? s)
      'done
      (begin 
	(proc (stream-car s))
	(stream-for-each proc (stream-cdr s)))))

(define (display-stream s)
  (stream-for-each display-line s))

(define (display-line x)
  (newline)
  (display x))

(define (stream-enumerate-interval low high)
  (if (> low high)
      the-empty-stream
      (cons-stream low
		   (stream-enumerate-interval (+ low 1) high))))

(define (stream-filter pred stream)
  (cond ((stream-null? stream) the-empty-stream)
	((pred (stream-car stream))
	 (cons-stream (stream-car stream)
		      (stream-filter pred (stream-cdr stream))))
	(else (stream-filter pred (stream-cdr stream)))))

(define (second-prime low high)
  (stream-car (stream-cdr (stream-filter prime? (stream-enumerate-interval low high)))))

;; exercise 3.50 
(define (general-stream-map proc . argstreams)
  (if (stream-null? (car argstreams))
      the-empty-stream
      (cons-stream
       (apply proc (map stream-car argstreams))
       (apply general-stream-map
	      (cons proc (map stream-cdr argstreams))))))


;;exercise 3.51
(define (show x)
  (display-line x)
  x)

(define exercise351 (stream-map show (stream-enumerate-interval 0 10)))

(define sum 0)

(define (accum x)
  (set! sum (+ x sum))
  sum)

(define seq (stream-map accum (stream-enumerate-interval 1 20)))

(define y (stream-filter even? seq))

(define z (stream-filter (lambda (x) (= (remainder x 5) 0)) seq))

(define (integers-starting-from n)
  (cons-stream n (integers-starting-from (+ n 1))))
(define integers (integers-starting-from 1))

(define (divisible? x y) (= (remainder x y) 0))

(define no-sevens (stream-filter (lambda (x) (not (divisible? x 7))) integers))

(define (fibgen a b)
  (cons-stream a (fibgen b (+ a b))))

(define fibs (fibgen 0 1))

;; extremely elegant program!!!! 
;; we should notice that cons-stream is of the form
;; (cons a (delay <b>))
(define (sieve stream)
  (cons-stream
   (stream-car stream)
   (sieve
    (stream-filter (lambda (x) (not (divisible? x (stream-car stream)))) 
		   (stream-cdr stream)))))

(define primes (sieve (integers-starting-from 2)))

(define ones (cons-stream 1 ones))

(define (add-streams s1 s2)
  (general-stream-map + s1 s2))

(define itgs (cons-stream 1 (add-streams ones itgs)))

(define s-fibs
  (cons-stream 0
	       (cons-stream 1 
			    (add-streams s-fibs
					 (stream-cdr s-fibs)))))

(define (scale-stream stream factor) 
  (stream-map (lambda (x) (* x factor)) stream))

(define double (cons-stream 1 (scale-stream double 2)))

(define prms
  (cons-stream 2
	       (stream-filter is-prime? (integers-starting-from 3))))

(define (is-prime n)
  (define (iter ps)
    (cond ((> (square (stream-car ps)) n) #t)
	  ((divisible? n (stream-car ps)) #f)
	  (else (iter (stream-cdr ps)))))
  (iter prms))


