# Bookstore
An e-commerce book store application written purely in JSP and Servlet using MVC model.

I wrote this a while ago when I was getting my hands dirty with JSP and Servlets.
This is great for college/university project. Don't use this for an actual website.

###### https://bookhive.herokuapp.com <- Live demo

### Dependencies
* Servlet, JSP, JSTL and EL APIs
* PostgreSQL JDBC Driver
* Jasypt - Encryption library I use to encrypt passwords.

### Features
* View books with category hierarchy
* Account creation and login (With validation and all)
* Book details page
* Review system
* Cart (Add, edit and delete items)
* Sorting of results (Bestsellers, Price (low), Price (High), Newest first)
* Search
* Authorization
* Pagination

There are copule of things you need to know if you're gonna use this. 

* This uses PostgreSQL for database management.
* Create database and create tables using the schema provided.
* In `Constants.java` enter your database details for JDBC connection.

After these steps you should be able to start your application. You will see nothing much though as there is no data yet.

### Data
I scraped data from http://amazon.in for this project. I scraped book details, summary, user reviews and categories of some 1000ish books.
I don't think I can upload it here. However, you can use this [scrapper](https://gist.github.com/RaghavPro/e9b5bc7f4feae7e4a116) I wrote.

##### Image covers
The script I mentioned above stores image covers in particular filename format. Which is `ISBN-n` where `n` is nth number of image for that `ISBN`. So if there are two images for one book image file names will be `*ISBNid*-1` and `*ISBNid*-2`.

You will have to store all images in `static/images/covers` folder.

##### User profile image
Uploading of profile image is not implemented yet but the system tries to get image `/static/images/users/userid.jpeg` in `cart.jsp`.
This process can be made secure by encrypting the id with application's secret key. Which you will have to define in `Constants.java`

### TODO
* ORM! Hibernate it.
* Use a JSP front-end framework like JSF
* User groups
* Admin panel
* Order creation (Half assed)
