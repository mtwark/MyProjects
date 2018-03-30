# Matthew Wark
# mtwark
# Lab 5: Decimal to Binary Converter
# 03/02/2018
# 01B Max Lichtenstein

# Pseudocode:
# 1. Get user input string from memory
# 2. Convert string to integer
# 3. Basically shift as far right as possible, and "look" at the LSB by using a mask to get rid of everything else.
# 4. Print the LSB after each shift.
#
# while(&char) != /0
# 	t3 = char
# 	t3 -= 45
#	move +1 on &char
#	t2 = horners()
#	if(neg)
#		t2 *= -1
#
# s0 = t2
# print(message1, s0)
# print(message2)
# 	
# for i in range(31, -1, -1):
#	shift_right t1, s0, i
#	and t1, 1
#	print t1


.data
user_input:	.asciiz "User input number:\n"
binary_num:	.asciiz "This number in binary is:\n"
newline:	.asciiz "\n"

.text

main:

# Didn't end up needing this function since I used Horner's Method, but it took me some time and it works, so I'm leaving it in.
# Function to get length of input string
	li	$t0, 0		# Counter = 0
	lw	$t4, ($a1)	# Load the address of the input argument
	# Loop will increment the address by one byte every iteration until it hits null character, indicating the end of the string
	get_len:
		lb	$t5, ($t4)		# Load the first byte from address stored in $t4
		beq	$t5, $zero, end_len	# If the current byte is null, break
		addiu	$t4, $t4, 1		# Add one to the address to check if the next byte is null
		addi	$t0, $t0, 1		# Add one to the counter that tracks string length
		b 	get_len			# Reset loop
	end_len:
	move	$s1, $t0		# Store string length in $s1

# Loop to gather each character of the string and convert from ascii to int
	li	$t0, 0			# $t0 will be the loop accumulator, initialize to 0
	lw	$t1, ($a1)		# Get the address initially pointed to by the address stored in $a1 (arg) and put it in $t5
	li	$t2, 0			# Initialize the "previous" result to 0. This is to force the first element in Horner's to just be that one element.
	li	$t9, 1			# This value will be 1 if positive, and -1 if negative
	
	input_loop:
		# Taking bytes
		lb	$t3, ($t1)		# Load the first byte stored at the address that is now in $t1, which contains our arg value and put in $t2
		beq	$t3, 45, neg_input	# Branch if byte is a "-" to handle negative numbers, sets $t9 to -1
		beq	$t3, $zero, end_input	# End loop if the current byte is null
		addiu	$t1, $t1, 1		# Move one byte over for the next iteration
		
		# Conversion using Horner's Method
		sub	$t3, $t3, 48		# Subtract 48 from the value pulled from the first byte to go from ascii to int
		li	$t4, 10			# Going to multiply our previous value by 10
		mulu	$t4, $t2, $t4		# Multiply result of previous loop iteration by 10 and store in $t4
		addu	$t2, $t4, $t3		# Add current byte to result of prev * 10
		
		b	input_loop		# Reset loop
	end_input:
	mul	$s0, $t2, $t9		# Multiply unsigned number by -1 if it was initially negative, else, by 1, then store in $s0
# End input gathering

# Outputting results in decimal and binary
	# Outputting message for user input
	li	$v0, 4			# Set to print string
	la	$a0, user_input		# Load address of our user input message to the argument
	syscall				# Execute^^
	li	$v0, 1			# Set to print integer
	move	$a0, $s0		# Move our stored integer into the argument for syscall
	syscall				# Execute^^
	
	# Output newline, then message for binary number
	li	$v0, 4			# Set to print string
	la	$a0, newline		# Load address of our newline character to the argument
	syscall				# Execute^^
	li	$v0, 4			# Set to print string
	la	$a0, binary_num		# Load address of our binary number message to the argument
	syscall				# Execute^^
	
	
	# Perform arithmetic shifts right to "look" at the MSBs moving from left to right
	li 	$t0, 31			# Loop accumulator, starting at 31 because we're shifting 31 bits
	shift_loop:
		srav 	$t1, $s0, $t0 		# Shift to right and look at LSB to basically pick out each bit
		and 	$t1, 1			# Set t1 to the LSB after shift
		li 	$v0, 1			# Print integer
		move 	$a0, $t1		# Put the result of the shift AND 1 into the arg for the print call
		syscall				# Execute^^
		sub 	$t0, $t0, 1		# Decrement loop accumulator
		bgt  	$t0, -1, shift_loop	# Reset loop until end of iteration where t0 = 0
		
	end_shift:			# Added for readability, I'm curious if it's considered necessary?
# End output	


	# Exit
	li 	$v0, 10
	syscall

neg_input:
	addiu	$t1, $t1, 1		# Move one byte over for the next iteration
	li	$t9, -1			# This will = -1 if negative, and 1 otherwise
	b	input_loop		# Return to the input loop
