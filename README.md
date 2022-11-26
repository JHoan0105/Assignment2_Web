NOVEMBER 26 UPDATE

Client login gui working and authentication working
ASYNC working on faces can update and create new sprites without breaking

Need to implement access for remote access
-JAAS ?

some resources
	https://www.virtuozzo.com/application-platform-docs/remote-access-to-ejb-glassfish/
	https://docs.oracle.com/middleware/1221/wls/SCPRG/fat_client.htm#SCPRG224


=======================================================================

NOVEMBER

NOTE - YOU MAY HAVE TO CLEAR CACHE EVERYTIME YOU ENTER PASSWORD
	on ERROR OR LOGIN

FOR BASIC AUTH

Make sure to have appuser users 
	with group name "Admin" or "RestGroup" or "JsfGroup"

=======================================================================

TO TEST FORM AUTH

go to Glassfish server -> server security -> realm -> file
								add user
								add user and groupname 
								(group name mapped to role-name in config)
go to ejb ->
	Source Packages ->
			entity ->
				-AppUserFacade
				-SpriteFacade
				//comment the declared roles and Roles allowed

go to ejb ->
	Source Packages ->
			service ->
				ApplicationCOnfig
				//comment all annotation on class EXCEPT APplicationPATH

got to war ->
	Web Pages ->
		 WEB-INF ->
			web.xml
			//comment out first 2 section of security constraint
			and uncomment <login-config> section with security constraint
				

==============================================================================================

# Assignment2_Web
Group Project for Assignment 2 in Web Enterprise Applications

# Making a Pull Request (PR)

<pre><code>
Contributors will need to be able to make changes to the codebase. The steps to do this are
outlined below:

1. The first step to create a PR is to fork the repository so that GitHub is aware that
you have duplicated it. To do this, click the drop down at the top of the GitHub page that
says "Fork" and click "Create a new fork". GitHub will create a forked repository in under
you GitHub profile, thus you should not have to do this step again.

2. Nothing will change once you've forked the repository. Follow the command under the "Cloning"
section of this README above to clone the repository.

3. The next step is to create a local feature branch. 
Run <strong>git checkout -b &#9001;branch_name&#9002;</strong> to create a new branch called
<em>branch_name</em>.

4. Make changes. Do git <strong>add &#9001;files&#9002;</strong> to stage all files that you changed. Alternatively, you can
use <strong>git add *</strong>, but please do not do this carelessly.

5. Make a commit e.g. <strong>git commit -m "commit message".</strong>

6. Push the feature branch to GitHub e.g. <strong>git push -u origin &#9001;branch_name&#9002;</strong>

7. If successful, GitHub will immediately recognize the new branch. Now it is time to create the
PR. Click on the "Pull Requests" tab on GitHub under the main project page. Click
"New pull request". On the RIGHT-HAND side click on the drop down that says "compare: main", and
select your feature branch. Click Create pull request. Enter a PR description of why you are
making a PR and a brief of what changes were made. Click submit to have it be forwarded to the
project owner for review.
</code></pre>
