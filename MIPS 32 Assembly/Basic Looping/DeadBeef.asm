# Matthew Wark
# mtwark@ucsc.edu
# Lab 4: DEADBEEF In MIPS
# 02/16/18
# 01B Max Lichtenstein


.data

prompt: 	.asciiz "Please enter a number N: "		# String displayed when prompting for user to input iterations
dead: 		.asciiz "DEAD"					# String displayed when i % 4 == 0
beef: 		.asciiz "BEEF"					# String displayed when i % 9 == 0
deadbeef: 	.asciiz "DEADBEEF"				# String displayed when i % 4 ==0 && i % 9 ==0
nl:		.asciiz "\n"					# Even though it's a bit of a clunky solution, makes lining up newlines easy

.text

# Get user input for n
la	$a0, prompt		# Loads the address of prompt variable
li	$v0, 4			# Set print string service
syscall				# Execute ^^

li	$v0, 5			# Get user input using read int service. This is our "N", stored in s0 below
syscall				# Execute^

move 	$s0, $v0		# Move users input from register v0 to register s0, where it will be saved
addi	$s0, $s0, 1		# Fixes off by one error and causes it to include the n given by adding one to user input
li	$t0, 1		    	# t0 = i. Initial value for loop accumulator set to 1, and stored in register t0

loop: 
	beq	$t0, $s0, end	# Loop test, if accumulator = user input, goto end
	bgt	$t0, 1, line	# Add new line at the start of every loop
	
	# Loop Body
	body:
		li 	$t4, 1		# Initialize register t4 to 1
		rem	$t1, $t0, 4	# Store the remainder of the division of our accumulator and 4 in t1
		rem	$t2, $t0, 9	# Store the remainder of the division of our accumulator and 9 in t2
		slt	$t1, $t1, $t4	# If (i % 4) < 1, Set t1 to 1
		slt	$t2, $t2, $t4	# If (i % 9) < 1, Set t2 to 1
		and	$t3, $t1, $t2	# Set t3 = t1 AND t2. This will return 1 iff both remainders were intially 0, and then set to 1 by the "slt" calls
		beq	$t3, $t4, db	# If t3 == 1, The remainder of each division must have been 0, so branch to db (DEADBEEF)
	
		rem	$t1, $t0, 4	# Store the remainder of the division of our accumulator and 4 in t1
		beq	$t1, 0, d	# If the remainder is 0, branch to d (DEAD)
	
		rem	$t1, $t0, 9	# Store the remainder of the division of our accumulator and 9 in t1
		beq	$t1, 0, bf	# If the remainder is 0, branch to bf (BEEF)
	
		li	$v0, 1		# If none of the above conditionals caused the loop to reset, i is not divisible by 4 or 9, so print i
		la	$a0, ($t0)	# Print the value of accumulator if other conditions aren't met
		syscall
	
		# Loop Reset
		addi 	$t0, $t0, 1	# Add one to out accumulator, i (t0), before resetting the loop. 
		bgtz 	$t0, loop	# goto loop. Techinically will only do so if t0 is > 0, which it always will be in this case since our range is from 1 -> n
	

# Conditionals
d:	# DEAD, print out "DEAD" if i was evenly divisible by 4
	addi 	$t0, $t0, 1		# Add one to accumulator before resetting loop
	li	$v0, 4			# Set to print string service	
	la	$a0, dead		# Print "DEAD"
	syscall				# Execute^^
	bgtz 	$t0, loop		# Reset loop after printing string
bf:	# BEEF, print out "BEEF" if i was evenly divisible by 9
	addi 	$t0, $t0, 1		# Add one to accumulator before resetting loop
	li	$v0, 4			# Set to print string service	
	la	$a0, beef		# Print "BEEF"
	syscall				# Execute^^
	bgtz 	$t0, loop		# Reset loop after printing string
db:	# DEADBEEF, print out "DEADBEEF" if i was evenly divisible by 4 and 9
	addi 	$t0, $t0, 1		# Add one to accumulator before resetting loop
	li	$v0, 4			# Set to print string service
	la	$a0, deadbeef		# Print "DEADBEEF"
	syscall				# Execute^^
	bgtz 	$t0, loop		# Reset loop after printing string
line:	# Add a new line before each for loop body, then goto loop body
	li	$v0, 4			# Set to print string service
	la	$a0, nl			# Print newline char
	syscall				# Execute^
	bgtz	$t0, body		# Return to the loop body
end:

li	$v0, 10			# Exit
syscall				# Execute^
