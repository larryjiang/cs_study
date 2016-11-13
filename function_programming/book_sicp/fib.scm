
;;;;Fibonacci Numbers

;(define (fib n)
  ;;calculate the nth fibonacci number recursively
 ; (if (< n 2)
  ;    1
   ;   (+ (fib (- n 1)) (fib (- n 2)))))

(define (factorial n)
  (if (< n 2)
    1
    (* n (factorial (dec n)))))
