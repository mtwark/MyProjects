# Matthew Wark
# mtwark
# Lab 6: Vigenere Cipher
# 03/11/2018
# 01B Max Lichtenstein

# Pseudo:
# EncryptChar(c, k):
#	bool upper = false
#	set both c and k to val from 0-25
#	if c + k > 25:
#		return = wrap(c + k)
#	return c + k
#
# EncryptString(s, k, r):
# r = ""
# for i in range(len(s)):
#	if i > len(k):
#		# reset to start of k string
#	r += EncryptChar(c[i], k[i])
# return r
#
# Decryptions are the exact same, just subtract in DecryptChar instead of adding, and wrap sum from bottom instead of top

.data

.text
# Function to encrypt a single character using a vigenere cipher
# t0 = input char
# t1 = key char
# t3 = lower bool

EncryptChar: nop

	move	$t0, $a0		# Load the given input char into t0
	move	$t1, $a1		# Load the given key char into t1
	
	li	$t3, 0			# Boolean variable used to indicate if an input char is lowercase

	# Get on a scale from 1-50 (A = 1,...,a= 27)
	subi	$t1, $t1, 65		# Subtract 65 from the key to give a num val for the letter from 0-25
	# If t0 < 91, sub 65
	bgt	$t0, 64, __upper	# If the input char is larger than 64, we'll consider it a char and check it's range later
	
	# If t0 > 96, sub 70 
	bgt	$t0, 96, __lower
	
	b	__return_char
	# Conditional arrivals
	__ifs:
		# If the number is > 64, consider it uppercase then check if it's lower
		__upper:
			bgt	$t0, 90, __lower	# If the number is larger than 90, consider it lowercase and check bounds later
			subi	$t0, $t0, 65		# Must be uppercase at this point, so sub 65 to get num val from 0-25
			b	__end_ifs		# End if
			
		# If the number is larger than 90, consider lowercase and then check range
		__lower:
			bgt	$t0, 122, __end_ifs	# If it's greater than 122, it's not a letter and should be skipped
			blt	$t0, 97, __end_ifs	# Skip punctuation marks that are for some reason between capital and lower letters
			li	$t3, 1			# Must be lowercase, set lowercase bool to true
			subi	$t0, $t0, 97		# Sub 97 to get num val from 0-25
			b	__end_ifs		# End if
	__end_ifs:
	
	
	# Add num values (0-26) to encrypt, using wrapping to take care of cases > 52
	add	$t2, $t0, $t1		# Add the input with the key 
	bgt	$t2, 25, __wrap		# If value is larger than 25 (Since 0 is included), wrap back to beg
	b	__end_wrap		# Else, skip wrapping
	
	# Loops back through the alphabet if it's out of range
	__wrap:
		subi	$t2, $t2, 26	# Sub 26 from sum to loop back
	__end_wrap:
	
	# Set back to regular ASCII values to finish encryption
	beq	$t3, 0, __capital	# If capital, add 65 to give uppercase letter back
	addi	$t2, $t2, 97		# Else add 97 to give lowercase letter back
	b	__return_char		# Branch to return statement
	
	# If capital
	__capital:
		addi	$t2, $t2, 65	
	
	# Just a way to skip the function if a letter isn't being encrypted
	__return_char:
		move	$v0, $t2	# Load our final ascii value into v0 to be returned
		jr	$ra		# Jump return to calling statement

# Function that iterates over given string, combines with a given Key, and modifies a2 to be the ciphered text
# s0 = Input string address
# s1 = Key string address
# s2 = Key string length
# t9 = Loop accumulator
# t8 = Current input string char
# t7 = Current key string char
# t6 = Loop counter for key char loop
# t5 = Calling function return address
EncryptString:	nop
	
	# Store previous s registers on stack
	subi	$sp, $sp, 12		# Allocate 12 bytes of memory on the stack for three 4 byte registers 
	sw	$s0, 8($sp)		# Place s0 on top of the stack
	sw	$s1, 4($sp)		# Place s1 on top of the stack
	sw	$s2, 0($sp)		# Place s2 on top of the stack
	

	# Setting variable defaults for the function		
	move	$t5, $ra		# Store return address in t5
	move	$s0, $a0		# Store the given input string address in s0
	move	$s1, $a1		# Store the given key string address in s1
	
	# Function to get length of key string
	li	$t0, 0			# Accumulator
	move	$t1, $s1		# Temp copy of key string address
	__get_len:
		lb	$t2, ($t1)		# Load the first byte in the string
		beq	$t2, $zero, __end_len	# If the current byte is null, break
		add	$t1, $t1, 1		# Add one to the address to check if the next byte is null
		addi	$t0, $t0, 1		# Add one to the counter that tracks string length
		b 	__get_len		# Reset loop
	__end_len:
	move	$s2, $t0		# Set s2 to the length of the key string
	
	# Main encryption function
	li	$t9, -1				# Counter
	move	$t6, $s2			# Reset the Key in loop when counter equals this value
	move	$t8, $s0			# Input string byte
	move	$t7, $s1			# Key string byte
	
	# While loop that runs until it hits a null term char in the input string
	__while_input:
		addi	$t9, $t9, 1		# Increment counter
		add	$t8, $s0, $t9		# Increment accross memory for input
		add	$t7, $s1, $t9		# Increment accross memory for key
		
		beq	$t9, $t6, __reset_key	# Branches to an if statement that basically resets the key to the start of the key string if in.len > key.len

		__while_body:
			lb	$t8, ($t8)			# Load the ith byte of the input string
			lb	$t7, ($t7)			# Load the ith byte of the key string
			beq	$t8, $zero, __end_while		# If the current byte is null, break
			
			# Prepare args for Encrypt char
			move	$a0, $t8			# Load ith byte of input string into a0
			move	$a1, $t7			# Load ith byte of key string (With loops considered) into a1
			jal	EncryptChar			# Encrypt char with given args
			
			# Storing the ciphered string
			sb	$v0, ($a2)			# Store the result of the Encrypted char in a2	
			addi	$a2, $a2, 1			# Move pointer in the result string over one for next char
		
			b 	__while_input			# Reset loop
		__end_while:
	__end_while:
	
	sub	$v1, $a2, $t9		# Return final string, starting at a2-input_str.len in v1
	
	# Restore saved values from stack
	lw	$s2, 0($sp)		# Restore s2
	lw	$s1, 4($sp)		# Restore s1
	lw	$s0, 8($sp)		# Restore s0
	
	addi	$sp, $sp, 12		# Free memory used by function on the stack
	
	jr	$t5			# Return to saved return address

# If the Key length is shorter than the input string length, reset to beginning of key string and calc the next loop counter val to reset at.
__reset_key:
	move	$t7, $s1		# Restore pointer to current key char to the initial address of the Key string (First value)
	add	$t6, $s2, $t6		# Add the length of the key to the counter value that the reset happened at to find next reset occurence
	b	__while_body		# Go back to while body


# Function to decrypt a single character using a vignere cipher
# t0 = input char
# t1 = key char
# t3 = lower bool

DecryptChar: nop

	move	$t0, $a0		# Load the given input char into t0
	move	$t1, $a1		# Load the given key char into t1
	
	li	$t3, 0			# Boolean variable used to indicate if an input char is lowercase

	# Get on a scale from 1-50 (A = 1,...,a= 27)
	subi	$t1, $t1, 65		# Subtract 65 from the key to give a num val for the letter from 0-26
	# If t0 < 91, sub 65
	bgt	$t0, 64, __Dupper	# If the input char is larger than 64, we'll consider it a char and check it's range later
	
	# If t0 > 96, sub 70 
	bgt	$t0, 96, __Dlower
	
	b	__Dreturn_char
	# Conditional arrivals
	__Difs:
		# If the number is > 64, consider it uppercase then check if it's lower
		__Dupper:
			bgt	$t0, 90, __Dlower	# If the number is larger than 90, consider it lowercase and check bounds later
			subi	$t0, $t0, 65		# Must be uppercase at this point, so sub 65 to get num val from 0-26
			b	__Dend_ifs		# End if
			
		# If the number is larger than 90, consider lowercase and then check range
		__Dlower:
			bgt	$t0, 122,__ Dend_ifs	# If it's greater than 122, it's not a letter and should be skipped
			blt	$t0, 97, __Dend_ifs	# Skip punctuation marks that are for some reason between capital and lower letters
			li	$t3, 1			# Must be lowercase, set lowercase bool to true
			subi	$t0, $t0, 97		# Sub 97 to get num val from 0-26
			b	__Dend_ifs		# End if
	__Dend_ifs:
	
	
	# Add num values (0-26) to encrypt, using wrapping to take care of cases > 52
	add	$t2, $t0, $t1		# Add the input with the key 
	bgt	$t2, 25, __Dwrap		# If value is larger than 25 (Since 0 is included), wrap back to beg
	b	__Dend_wrap		# Else, skip wrapping
	
	# Loops back through the alphabet if it's out of range
	__Dwrap:
		subi	$t2, $t2, 26	# Sub 26 from sum to loop back
	__Dend_wrap:
	
	# Set back to regular ASCII values to finish encryption
	beq	$t3, 0, __Dcapital	# If capital, add 65 to give uppercase letter back
	addi	$t2, $t2, 97		# Else add 97 to give lowercase letter back
	b	__Dreturn_char		# Branch to return statement
	
	# If capital
	__Dcapital:
		addi	$t2, $t2, 65	
	
	# Just a way to skip the function if a letter isn't being encrypted
	__Dreturn_char:
		move	$v0, $t2	# Load our final ascii value into v0 to be returned
		jr	$ra		# Jump return to calling statement

# Function that iterates over given string, combines with a given Key, and modifies a2 to be the deciphered text
# s0 = Input string address
# s1 = Key string address
# s2 = Key string length
# t9 = Loop accumulator
# t8 = Current input string char
# t7 = Current key string char
# t6 = Loop counter for key char loop
# t5 = Calling function return address
DecryptString:	nop
	
	# Store previous s registers on stack
	subi	$sp, $sp, 12		# Allocate 12 bytes of memory on the stack for three 4 byte registers 
	sw	$s0, 8($sp)		# Place s0 on top of the stack
	sw	$s1, 4($sp)		# Place s1 on top of the stack
	sw	$s2, 0($sp)		# Place s2 on top of the stack
	

	# Setting variable defaults for the function		
	move	$t5, $ra		# Store return address in t5
	move	$s0, $a0		# Store the given input string address in s0
	move	$s1, $a1		# Store the given key string address in s1
	
	# Function to get length of key string
	li	$t0, 0			# Accumulator
	move	$t1, $s1		# Temp copy of key string address
	__Dget_len:
		lb	$t2, ($t1)		# Load the first byte in the string
		beq	$t2, $zero, __Dend_len	# If the current byte is null, break
		add	$t1, $t1, 1		# Add one to the address to check if the next byte is null
		addi	$t0, $t0, 1		# Add one to the counter that tracks string length
		b 	__Dget_len		# Reset loop
	Dend_len:
	move	$s2, $t0		# Set s2 to the length of the key string
	
	# Main encryption function
	li	$t9, -1				# Counter
	move	$t6, $s2			# Reset the Key in loop when counter equals this value
	move	$t8, $s0			# Input string byte
	move	$t7, $s1			# Key string byte
	
	# While loop that runs until it hits a null term char in the input string
	__Dwhile_input:
		addi	$t9, $t9, 1		# Increment counter
		add	$t8, $s0, $t9		# Increment accross memory for input
		add	$t7, $s1, $t9		# Increment accross memory for key
		
		beq	$t9, $t6, __Dreset_key	# Branches to an if statement that basically resets the key to the start of the key string if in.len > key.len

		__Dwhile_body:
			lb	$t8, ($t8)			# Load the ith byte of the input string
			lb	$t7, ($t7)			# Load the ith byte of the key string
			beq	$t8, $zero, __Dend_while		# If the current byte is null, break
			
			# Prepare args for Encrypt char
			move	$a0, $t8			# Load ith byte of input string into a0
			move	$a1, $t7			# Load ith byte of key string (With loops considered) into a1
			jal	DecryptChar			# Encrypt char with given args
			
			# Storing the ciphered string
			sb	$v0, ($a2)			# Store the result of the Encrypted char in a2	
			addi	$a2, $a2, 1			# Move pointer in the result string over one for next char
		
			b 	__Dwhile_input			# Reset loop
		__Dend_while:
	__Dend_while:
	
	sub	$v1, $a2, $t9		# Return final string, starting at a2-input_str.len in v1
	
	# Restore saved values from stack
	lw	$s2, 0($sp)		# Restore s2
	lw	$s1, 4($sp)		# Restore s1
	lw	$s0, 8($sp)		# Restore s0
	
	addi	$sp, $sp, 12		# Free memory used by function on the stack
	
	jr	$t5			# Return to saved return address

# If the Key length is shorter than the input string length, reset to beginning of key string and calc the next loop counter val to reset at.
__Dreset_key:
	move	$t7, $s1		# Restore pointer to current key char to the initial address of the Key string (First value)
	add	$t6, $s2, $t6		# Add the length of the key to the counter value that the reset happened at to find next reset occurence
	b	__Dwhile_body		# Go back to while body
