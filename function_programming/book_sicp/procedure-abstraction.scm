(define (jsqrt x)
  (define (average x y) (/ (+ x y) 2))
  (define (good-enough? guess)
    (< (abs (- (square guess) x)) 0.001))
  (define (improve guess)
    (average guess (/ x guess)))
  (define (sqrt-iter guess)
    (if (good-enough? guess)
	guess
	(sqrt-iter (improve guess))))
  (sqrt-iter 1.0))


(define (factorial-rec n)
  (if (= n 1)
      1
      (* n (factorial-rec (- n 1)))))

(define (factorial-iter product counter max-count)
  (if (> counter max-count)
      product
      (factorial-iter (* product counter) (+ counter 1) max-count)))

(define (factorial-n n)
  (factorial-iter 1 1 n))

(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
	 (sum term (next a) next b))))

(define (pi-sum a b)
  (define (pi-term x)
    (/ 1.0 (* x (+ x 2))))
  (define (pi-next x) 
    (+ x 4))
  (sum pi-term a pi-next b))

(define (integral f a b dx) 
  (define (add-dx x) (+ x dx))
  (* (sum f (+ a (/ dx 2)) add-dx b) dx))

(define (cube x) (* x x x))
(define (positive? x) (> x 0))
(define (negative? x) (< x 0))
(define (search f neg-point pos-point)
  (define (average x y) (/ (+ x y) 2))
  (let ((mid-point (average neg-point pos-point)))
  (if (close-enough? neg-point pos-point 0.001)
      mid-point
      (let ((test-value (f mid-point)))
      (cond ((positive? test-value) (search f neg-point mid-point))
	    ((negative? test-value) (search f mid-point pos-point))
	    (else mid-point))))))


(define (half-interval-method f a b)
  (let ((a-value (f a))
	(b-value (f b)))
    (cond ((and (negative? a-value) (positive? b-value)) (search f a b))
	  ((and (negative? b-value) (positive? a-value)) (search f b a))
	  (else (error "Values are not of opposite sign" a b)))))



(define (close-enough? x y margin)
  (< (abs (- x y)) margin))

(define tolerance 0.00001)
(define (fixed-point f first-guess)
  (define (try guess)
    (let ((next (f guess)))
      (if (close-enough? guess next tolerance)
	  next
	  (try next))))
  (try first-guess))

(define (fixed-point-sqrt x)
  (define (average x y) (/ (+ x y) 2))
  (fixed-point (lambda (y) (average y (/ x y))) 1.0))

(define (average x y) (/ (+ x y) 2))
(define (average-damp f) 
  (lambda (x) (average x (f x))))
(define (deriv g)
  (lambda (x) (/ (- (g (+ x dx)) (g x)) dx)))
(define dx 0.000001)
(define (newton-transform g)
  (lambda (x) (- x (/ (g x) ((deriv g) x)))))

(define (newton-method g guess)
  (fixed-point (newton-transform g) guess))

(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
	((divides? test-divisor n) test-divisor)
	(else (find-divisor n (+ test-divisor 1)))))

(define (smallest-divisor n)
  (find-divisor n 2))

(define (divides? a b)
  (= (remainder b a) 0))

(define (prime? n)
  (= n (smallest-divisor n)))




;(define (square-list items)
;  (if (null? items)
;      '()
;      (cons (square (car items)) (square-list items))))
