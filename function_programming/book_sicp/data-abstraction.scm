(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
	       (* (numer y) (denom x)))
	    (* (denom x) (denom y))))
(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
	       (* (numer y) (denom x)))
	    (* (denom x) (denom y))))

(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
	    (* (denom x) (denom y))))

(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
	    (* (denom x) (numer y))))
(define (equal-rat x y)
  (= (* (numer x) (denom y))
     (* (denom x) (numer y))))
(define (make-rat n d)
  (let ((g (gcd (abs n) (abs d))))
    (cond ((= d 0) (error "denominator can not be 0!")))
    (if (> (* n d) 0)
	(cons  (/ (abs n) g) (/ (abs d) g))
	(cons (- 0 (/ (abs n) g)) (/ (abs d) g)))))
(define (numer x)
  (car x))
(define (denom x)
  (cdr x))
(define (print-rat x)
  (newline)
  (display (numer x))
  (display "/")
  (display (denom x)))
;greatest common divider
(define (gcd x y)
  (if (= y 0)
      x
      (gcd y (remainder x y))))
(define one-to-four (list 1 2 3 4))

(define (list-ref items n)
  (if (= n 0) 
      (car items)
      (list-ref (cdr items) (- n 1))))
(define (length terms) 
  (if (null? terms) 
      0
      (+ 1 (length (cdr terms)))))
(define (length-iter terms)
  (define (inner-length-iter t count)
    (if (null? t)
	count
	(inner-length-iter (cdr t) (+ 1 count))))
(inner-length-iter terms 0))

;exercise 2.17
(define (last-pair terms) 
  (if (null? (cdr terms))
      (car terms)
      (last-pair (cdr terms))))
;exercise 2.18
(define (reverse terms)
  (define (reverse-add l1 l2)
    (if (null? (cdr l1))
	(append l2 (list (car l1)))
	(append (reverse-add (cdr l1) l2) (list (car l1)))))
(reverse-add terms ()))
;exercise 2.27
(define (deep-reverse terms)
  (if (or (null? terms) (not (list? terms)))
      terms
      (reverse (cons (deep-reverse (car terms)) 
		       (deep-reverse (cdr terms))))))

;exercise 2.28
(define (fringe terms)
  (cond ((null? terms) ())
	((not (list? terms)) (list terms))
	(else (append (fringe (car terms)) (fringe (cdr terms)))))) 

;exercise 2.20
(define (same-parity . parameters)
  (define (inner-sp holder e? paras)
    (if (null? paras)
	holder
	(if (eqv? e? (even? (car paras)))
	    (inner-sp (append holder (list (car paras))) e? (cdr paras))
	    (inner-sp holder e? (cdr paras)))))
  (inner-sp () (even? (car parameters)) parameters ))

(define (scale-list items factor) 
  (if (null? items)
      ()
      (cons (* (car items) factor)
	    (scale-list (cdr items) factor))))

;define a map of my own
(define (j-map proc items) 
  (if (null? items)
      '()
      (cons (proc (car items))
	    (j-map proc (cdr items)))))

;count leaves

(define (count-leaves x) 
  (cond ((null? x) 0)
	((not (pair? x)) 1)
	(else (+ (count-leaves (car x))
		 (count-leaves (cdr x))))))
;; Mapping over trees
(define (scale-tree tree factor)
  (cond ((null? tree) '())
	((not (pair? tree)) (* tree factor))
	(else (cons (scale-tree (car tree) factor) 
		    (scale-tree (cdr tree) factor)))))
(define (scale-tree-map tree factor)
  (map (lambda (sub-tree) 
	 (if (pair? sub-tree)
	     (scale-tree-map sub-tree factor)
	     (* sub-tree factor))) tree))

;exerices 2.30
(define (square-tree tree)
  (cond ((null? tree) '())
	((not (pair? tree)) (* tree tree))
	(else (cons (square-tree (car tree))
		    (square-tree (cdr tree))))))

(define (square-tree-map tree)
  (map (lambda (sub-tree) 
	 (if (pair? sub-tree)
	     (square-tree-map sub-tree)
	     (* sub-tree sub-tree))) tree))


;exercise 2.31
(define (tree-map procedure tree)
  (map (lambda (sub-tree)
	 (if (pair? sub-tree)
	     (tree-map procedure sub-tree)
	     (procedure sub-tree))) tree))

(define (square-tree-abstraction tree) (tree-map square tree))

;exercise 2.32
(define (subsets s)
  (if (null? s)
      (list '())
      (let ((rest (subsets (cdr s))))
	(append rest (map (lambda (sub-set) (append sub-set (list (car s)))) rest)))))

;example in the book
(define (sum-odd-squares tree)
  (cond ((null? tree) 0)
	((not (pair? tree)) 
	 (if (odd? tree)
	     (square tree)
	     0))
	(else (+ (sum-odd-squares (car tree))
		 (sum-odd-squares (cdr tree))))))

;example in the book
(define (even-fibs n) 
(define (next k)
    (if (> k n)
	'()
	(let ((f (fib k)))
	  (if (even? f)
	      (cons f (next (+ k 1)))
	      (next (+ k 1))))))
  (next 0))


(define (filter predicate sequence)
  (cond ((null? sequence) '())
	 ((predicate (car sequence)) 
	  (cons (car sequence) (filter predicate (cdr sequence))))
	 (else (filter predicate (cdr sequence)))))

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
	  (accumulate op initial (cdr sequence)))))
;exercie 2.33
(define (m-map p sequence)
  (accumulate (lambda (x y) (cons (p x) y)) '() sequence))
 
(define (m-append seq1 seq2)
  (accumulate cons seq2 seq1))

(define (m-length sequence)
  (accumulate (lambda (x y) (+ 1 y)) 0 sequence))
;exercise 2.34

(define (horner-eval x coefficient-sequence)
  (accumulate (lambda (this-coeff higher-terms) (+ this-coeff (* x higher-terms)))
	      0
	      coefficient-sequence))
;exercise 2.35
(define (m-count-leaves t)
  (accumulate + 0 (map (lambda (subtree) (cond ((null? subtree) 0)
					       ((not (pair? subtree)) 1)
					       (else (m-count-leaves subtree)))) t)))
;exercise 2.36
(define (accumulate-n op init seqs)
  (if (null? (car seqs))
      '()
      (cons (accumulate op init (map (lambda (subtree) (car subtree)) seqs))
	    (accumulate-n op init (map (lambda (subtree) (cdr subtree)) seqs)))))

;exercise 2.37
(define (dot-product v w)
  (accumulate + 0 (map * v w)))

(define (matrix-*-verctor m v)
  (map (lambda (sub-vector) (dot-product sub-vector v)) m))

(define (transpose mat)
  (accumulate-n cons '() mat))

(define (matrix-*-matrix m n)
  (let ((cols (transpose n)))
    (map (lambda (sub-vector) 
	   (map (lambda (col-sub-vector) (dot-product sub-vector col-sub-vector)) cols)) m)))
;exercise 2.38

(define (fold-left op initial sequence)
  (define (iter result rest)
    (if (null? rest)
	result
	(iter (op result (car rest))
	      (cdr rest))))
  (iter initial sequence))

(define fold-right accumulate)

;exercise 2.39

(define (to-list y)
  (if (list? y)
      y
      (list y)))

(define (r-reverse sequence)
  (fold-right (lambda (x y) (append (to-list y) (to-list x))) '() sequence))

(define (l-reverse sequence)
  (fold-left (lambda (x y) (append (to-list y) (to-list x))) '() sequence))

;NESTED MAPPINGS
(define (enumerate-interval low high) 
  (if (> low high)
      '()
      (cons low (enumerate-interval (+ low 1) high))))


(define (unique-pairs n)
  (map (lambda (i) (map (lambda (j) (list i j)) (enumerate-interval 1 (- i 1)))) (enumerate-interval 1 n)))

(define (unique-triples n)
  (flat-map (lambda (i) (flat-map (lambda (j) (map (lambda (k) (list i j k)) (enumerate-interval 1 n))) (enumerate-interval 1 n))) (enumerate-interval 1 n)))

(define (generate-pairs-verbose n)
  (accumulate append '() 
	      (map (lambda (i) 
		     (map (lambda (j) (list i j)) (enumerate-interval 1 (- i 1)))) (enumerate-interval 1 n))))

(define (generate-pairs-simple n)
  (accumulate append '() (unique-pairs n)))

(define (flat-map proc seq)
  (accumulate append '() (map proc seq)))

(define (prime-sum? pair)
  (prime? (+ (car pair) (cadr pair))))

(define (make-pair-sum pair)
  (list (car pair) (cadr pair) (+ (car pair) (cadr pair))))

(define (prime-sum-pairs n)
  (map make-pair-sum (filter prime-sum? (flat-map (lambda (i)
						  (map (lambda (j) (list i j)) (enumerate-interval 1 (- i 1)))
						  ) (enumerate-interval 1 n)))))

(define (permutations s)
  (if (null? s)
      (list '())
      (flat-map (lambda (x) (map (lambda (p) (cons x p)) 
				 (permutations (remove x s)))) 
		s)))
(define (remove item sequence)
  (filter (lambda (x) (not (= x item))) sequence))

;exercise 2.40
;done, see above

;exercise 2.41
(define (distinct-triple? seq)
  (and (not (= (car seq) (cadr seq))) (not (= (cadr seq) (caddr seq))) (not (= (car seq) (caddr seq)))))
(define (triple-sum seq)
  (+ (car seq) (cadr seq) (caddr seq)))

(define (triple-sum-pairs n s)
 (filter distinct-triple? (filter (lambda (x) (= s (triple-sum x)))  (unique-triples n))))


;exercise 2.42

;exercise 2.43


;;Section 2.3 Symbolic Data
;exercise 2.54

(define (j-equal? list-a list-b)
  (cond ((and (null? list-a) (null? list-b)) #t)
        ((and (symbol? list-a) (symbol? list-b)) (eq? list-a list-b))
	((and (list? list-a) (list? list-b)) (and (j-equal? (car list-a) (car list-b)) (j-equal? (cdr list-a) (cdr list-b))))
	(else #f)))

;; Sector 2.3.2

(define (variable? x) (symbol? x))

(define (same-variable? v1 v2)
  (and (variable? v1) (variable? v2) (eq? v1 v2)))

(define (make-sum a1 a2) 
  (cond ((=number? a1 0) a2)
	((=number? a2 0) a1)
	((and (number? a1) (number? a2)) (+ a1 a2))
	(else (list '+ a1 a2))))

(define (ifx-make-sum a1 a2) 
  (cond ((=number? a1 0) a2)
	((=number? a2 0) a1)
	((and (number? a1) (number? a2)) (+ a1 a2))
	(else (list a1 '+ a2))))



(define (make-sum-of-list exp)
  (cond ((null? exp) 0)
	(else (make-sum (car exp) (make-sum-of-list (cdr exp))))))

(define (sum-of-list exp)
  (if (sum? exp)
      (make-sum-of-list (cdr exp))
      (error "not a sum")))

(define (make-product-of-list exp)
  (cond ((null? exp) 1)
	(else (make-product (car exp) (make-product-of-list (cdr exp))))))

(define (product-of-list exp)
  (if (product? exp)
      (make-sum-of-list (cdr exp))
      (error "not a product")))


(define (=number? exp num)
  (and (number? exp) (= exp num)))



(define (make-product m1 m2) 
  (cond ((or (=number? m1 0) (=number? m2 0)) 0)
	((=number? m1 1) m2)
	((=number? m2 1) m1) 
	((and (number? m1) (number? m2)) (* m1 m2))
	(else (list '* m1 m2))))




(define (ifx-make-product m1 m2) 
  (cond ((or (=number? m1 0) (=number? m2 0)) 0)
	((=number? m1 1) m2)
	((=number? m2 1) m1) 
	((and (number? m1) (number? m2)) (* m1 m2))
	(else (list m1 'x  m2))))


(define (exponentiation? x) (and (pair? x) (eq? (car x) '**)))

(define (make-exponentiation base exponent)
  (cond ((=number? exponent 0) 1)
	((=number? exponent 1) base)
	(else (list '** base exponent))))

(define (base exp) (cadr exp))

(define (exponent exp) (caddr exp))


(define (sum? x) (and (pair? x) (eq? (car x) '+)))

(define (ifx-sum? x) (and (pair? x) (eq? (cadr x) '+)))

(define (ifx-product? x) (and (pair? x) (eq? (cadr x) '*)))

(define (ifx-addend s) (car s))

(define (ifx-augend s) (caddr s))

(define (ifx-multiplier p) (car p))

(define (ifx-multiplicand p) (caddr p))





(define (addend s) (cadr s))

;(define (augend s) (caddr s))

(define (augend s) (make-sum-of-list (cddr s)))

(define (product? x) (and (pair? x) (eq? (car x) '*)))

(define (multiplier p) (cadr p))

;(define (multiplicand p) (caddr p))

(define (multiplicand p) (make-product-of-list (cddr p)))

(define (deriv exp var)
  (cond ((number? exp) 0)
	((variable? exp) 
	 (if (same-variable? exp var) 1 0))
	((sum? exp)
	 (make-sum (deriv (addend exp) var)
		   (deriv (augend exp) var)))
	((product? exp) 
	 (make-sum 
	  (make-product (multiplier exp)
			(deriv (multiplicand exp) var))
	  (make-product (deriv (multiplier exp) var)
			(multiplicand exp))))
	((exponentiation? exp)
	 (make-product (exponent exp) (make-product (make-exponentiation (base exp) (- (exponent exp) 1)) (deriv (base exp) var))))
	(else 
	 (error "unknown expression type -- DERIV" exp))))

(define (ifx-deriv exp var)
  (cond ((number? exp) 0)
	((variable? exp) 
	 (if (same-variable? exp var) 1 0))
	((ifx-sum? exp)
	 (ifx-make-sum (ifx-deriv (ifx-addend exp) var)
		   (ifx-deriv (ifx-augend exp) var)))
	((ifx-product? exp) 
	 (ifx-make-sum 
	  (ifx-make-product (ifx-multiplier exp)
			(ifx-deriv (ifx-multiplicand exp) var))
	  (ifx-make-product (ifx-deriv (ifx-multiplier exp) var)
			(ifx-multiplicand exp))))
	((exponentiation? exp)
	 (ifx-make-product (exponent exp) (ifx-make-product (make-exponentiation (base exp) (- (exponent exp) 1)) (ifx-deriv (base exp) var))))
	(else 
	 (error "unknown expression type -- DERIV" exp))))





;;exercise 2.56 done and merged above

;;exercise 2.57 done and merged above

;;exercise 2.58 


