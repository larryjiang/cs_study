;;;This file contains source code for section 3.4 of sicp book
(define (make-wire)
  (let ((signal-value 0) (action-procedures '()))
    (define (set-my-signal! new-value)
      (if (not (= signal-value new-value))
	  (begin (set! signal-value new-value)
		 (call-each action-procedures))
	  'done))
    (define (accept-action-procedure! proc)
      (set! action-procedures (cons proc action-procedures)))
    (define (dispatch m)
      (cond ((eq? m 'get-signal) signal-value)
	    ((eq? m 'set-signal!) set-my-signal!)
	    ((eq? m 'add-action!) accept-action-procedure!)
	    (else (error "Unknown operation -- WIRE" m))))
    dispatch))



(define (call-each procedures)
  (if (null? procedures)
      'done
      (begin 
	((car procedures))
	(call-each (cdr procedures)))))


(define (get-signal wire)
  (wire 'get-signal))

(define (set-signal! wire new-value)
  ((wire 'set-signal!) new-value))

(define (add-action! wire action-procedure)
  ((wire 'add-action!) action-procedure))

(define (half-adder a b s c)
  (let ((d (make-wire)) (e (make-wire)))
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)
    'ok))

(define (logical-not s)
  (cond ((= s 0) 1)
	((= s 1) 0)
	(else (error "Invalid signal" s))))

(define (inverter input output)
  (define (invert-input)
    (let ((new-value (logical-not (get-signal input))))
      (after-delay inverter-delay 
		   (lambda () 
		     (set-signal! output new-value)))))
  (add-action! input invert-input)
  'ok)

(define (and-gate a1 a2 output)
  (define (and-action-procdure)
    (let ((new-value (logical-and (get-signal a1) (get-signal a2))))
      (after-dalay and-gate-delay 
		   (lambda () 
		     (set-signal! output new-value)))))
  (add-action! a1 and-action-procedure)
  (add-action! a2 and-action-procedure)
  'ok)

(define (logical-and a1 a2)
  (cond ((and (= a1 1) (= a2 1)) 1)
	((and (= (* a1 a2) 0) (= (+ a1 a2) 1)) 0)
	((and (= a1 0) (= a2 0)) 0)
	(else (error "invalid arguments" a1 a2))))

(define (logical-or a1 a2)
  (cond ((and (= a1 0) (= a2 0)))
	((and (= (* a1 a2) 0) (= (+ a1 a2) 1)) 1)
	((and (= a1 1) (= a2 1)) 1)
	(else (error "invalid arguments" a1 a2))))

(define (or-gate a1 a2 output)
  (define (add-action-procedure)
    (let ((new-value 
	   (logical-or (get-signal a1) (get-signal a2))))
      (after-delay or-gate-delay 
		   (lambda () 
		     (set-signal! output new-value)))))
  (add-action! a1 and-action-procedure)
  (add-action! a2 and-action-procedure)
  'ok)

(define (and-gate a1 a2 output)
  (define (add-action-procedure) 
    (let ((new-value 
	   (logical-and (get-signal a1) (get-signal a2))))
      (after-delay and-gate-delay
		   (lambda ()
		     (set-signal! output new-value)))))
  (add-action! a1 and-action-procedure)
  (add-action! a2 and-action-procedure)
  'ok)


(define (compound-or-gate a1 a2 output)
  (define (and-action-procedure) (let ((a1-r (make-wire))
	(a2-r (make-wire))
	(a1-a2-r (make-wire))
	(pre-output (make-wire)))
    (inverter a1 a1-r)
    (inverter a2 a2-r)
    (and-gate a1-r a2-r a1-a2-r)
    (inverter a1-a2-r pre-out)
    (after-dalay (+ (* 3 inverter-delay) and-gate-delay)
		 (lambda ()
		   (set-signal! output (get-signal pre-out))))))
  (add-action! a1 add-action-procedure)
  (add-action! a2 add-action-procedure)
  'ok)

;; I am not sure this is right, but it occurs to me that in writing (recursive)
;; application, there are only two ways to communicate between chained function
;; calls if no global variables are involved.
;; One way is to pass parameters from the calling function to the callee function
;; The other way is to return value from the callee function to the caller function
;; This is too obvious to be noticed normally. However, I think this is very important
;; in programming designs.
;; current program is not good since it is not tail-recursive and hence it 
;; may cause stack overflow on very big list.
(define (ripple-carry-adder input-list-a input-list-b output-list-c carry)
  (define (rca a b c)
    (if (null? (cdr a))
	carry
	(let ((c-out (make-wire))) 
	     (full-adder (car a) (car b) (rca (cdr a) (cdr b) (cdr c)) (car c) c-out)
	     cout)
	))
  (set-signal! carry (get-signal (rca input-list-a input-list-b output-list-c))))






(define (half-adder a b s c)
  (let ((d (make-wire)) (e (make-wire)))
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)
    'ok))

(define (full-adder a b c-in sum c-out)
  (let ((s (make-wire))
	(c1 (make-wire))
	(c2 (make-wire)))
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)
    'ok))
