Project Name: NegotiateWith.me

### Entities 

* User
	1. id 
	2. name
	3. email_id
	4. password

* Profile
	1. id
	2. user_id
	3. is_seeker
	4. is_hunter
	5. date_of_birth

* Resume
	1. id
	2. profile_id
	3. current_ctc

* Education
	1. id
	2. resume_id
	3. type (School, Bachelors, Masters etc.)
	4. subjects
	5. name_of_institution
	6. name_of_university
	7. attended_from
	8. attended_to

* Experience
	1. id
	2. profile_id
	3. organisation_name
	4. position
	5. start_date
	6. end_date
	7. description

* Skill
	1. id
	2. resume_id
	3. name


APIs Required

- Signup (Name, Email, Password, hunter/seeker/both, date_of_birth)
- Login (email, password) -> Basic Authentication
- /users/{userId}/worth
	* find profiles matching current experience in years [0, 1, 2...]
	* for profiles found create a Map<Set<Skills>, CTC>
