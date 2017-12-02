	.file	"simple_l.c"
	.globl	gval1
	.data
	.align 8
	.type	gval1, @object
	.size	gval1, 8
gval1:
	.quad	567
	.globl	gval2
	.align 8
	.type	gval2, @object
	.size	gval2, 8
gval2:
	.quad	763
	.text
	.globl	simple_l
	.type	simple_l, @function
simple_l:
.LFB0:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movq	%rdi, -24(%rbp)
	movq	%rsi, -32(%rbp)
	movq	-24(%rbp), %rax
	movq	(%rax), %rdx
	movq	-32(%rbp), %rax
	addq	%rdx, %rax
	movq	%rax, -8(%rbp)
	movq	-24(%rbp), %rax
	movq	-8(%rbp), %rdx
	movq	%rdx, (%rax)
	movq	-8(%rbp), %rax
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	simple_l, .-simple_l
	.globl	call_simple_l
	.type	call_simple_l, @function
call_simple_l:
.LFB1:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$16, %rsp
	movl	$12, %esi
	movl	$gval1, %edi
	call	simple_l
	movq	%rax, -8(%rbp)
	movq	gval2(%rip), %rdx
	movq	-8(%rbp), %rax
	addq	%rdx, %rax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	call_simple_l, .-call_simple_l
	.ident	"GCC: (Ubuntu 5.4.0-6ubuntu1~16.04.4) 5.4.0 20160609"
	.section	.note.GNU-stack,"",@progbits
