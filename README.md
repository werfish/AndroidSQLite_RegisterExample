# AndroidSQLite_RegisterExample
This is an example for a user Registration screen implementing some basic user input validation,SQLite database and enrypting passwords with
SHA-256 algorithm and password salting. This application is created for practice reasons and will be a part of a future tutorial.

The application does not use threads for running queries for simplicity reasons. The same reasons apply to not using fragments.

# Functionality/Requirements of the app
Aplication starts up with the resgistration activity which contains fields for Name,Email,Phone,Password and repeated password. These fields
all implement the custom field types for each categories such as number keypad for Phone field and * for passwords. At the bottom there are 
2 buttons. 1st one is Add which launches validation and if all validation criteria are passed, then all field values are collected and 
inserted into the SQLite database. 

The 2nd button Record List opens another activity containing a list view showing all names of users in
the database. When clicked on the list item a detail activity is launched showing the User_id, name , phone number, Password unencrypted, passwrord after
SHA-256 encryption and then the passwrod after Salting.

# Validation Logic
- Is Name field empty? if not 
- Run a query to check if the Name exists in the database? If does not exist
- Is the email field empty? if not
- Run a query to check if the Email already exists in the database? If does not exists
- Check if Password field is empty? If not
- Check if the password and RepeatedPassword field values match? If they do then
Insert the records to the database

On failing any of the tests, validation is stopped and a Toast is shown to the user containing apriopriate message.
# Good Sources on Password Encryption and Practices

Some sources I used to learn about password encryption.

http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/

https://crackstation.net/hashing-security.htm

https://www.owasp.org/index.php/Password_Storage_Cheat_Sheet
