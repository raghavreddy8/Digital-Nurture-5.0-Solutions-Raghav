CREATE database upskilling;
USE upskilling;

-- CREATING THE DATABASE SCHEMA 
CREATE TABLE Users(
	user_id INT PRIMARY KEY auto_increment,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

CREATE TABLE Events(
	event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100),
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM("upcoming","completed","cancelled"),
    organizer_id INT,
    CONSTRAINT rf_userid
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

CREATE TABLE Sessions(
	session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(200) NOT NULL,
    speaker_name VARCHAR(200) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    CONSTRAINT rf_event FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Registrations(
	registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    CONSTRAINT rf_reg_user FOREIGN KEY (user_id) REFERENCES Users(user_id),
    CONSTRAINT rf_reg_event FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Feedback(
	feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT,
    comments TEXT,
    feedback_date DATE NOT NULL,
    CONSTRAINT rf_fb_user FOREIGN KEY (user_id) REFERENCES Users(user_id),
    CONSTRAINT rf_fb_event FOREIGN KEY (event_id) REFERENCES Events(event_id),
    CONSTRAINT chck_rating CHECK(rating>=1 AND rating<=5)
);

CREATE TABLE Resources(
	resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_type ENUM("pdf","image","link"),
    resource_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    CONSTRAINT rf_rs_event FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

DESC Users;
DESC Events;
DESC Sessions;
DESC Registrations;
DESC Feedback;
DESC Resources;

-- Fixing the Events.city allowing NULL value.
ALTER TABLE Events
MODIFY city varchar(100) NOT NULL;

-- INSERTING VALUES INTO TABLES 
INSERT INTO Users()
VALUES
(1,"Alice Johnson","alice@example.com","New York","2024-12-01"),
(2,"Bob Smith","bob@example.com","Los Angeles","2024-12-05"),
(3,"Charlie Lee","charlie@example.com","Chicago","2024-12-10"),
(4,"Diana King","diana@example.com","New York","2025-01-15"),
(5,"Ethan Hunt","ethan@example.com","Los Angeles","2025-02-01")
;

INSERT INTO Events()
VALUES
(1,'Tech Innovators Meetup','A meetup for tech enthusiasts.','New York','2025-06-10 10:00:00','2025-06-10','upcoming',1),
(2,'AI & ML Conference','Conference on AI and ML advancements.','Chicago','2025-05-15 09:00:00','2025-05-15 17:00:00',"completed",3),
(3,'Frontend Development Bootcamp','Hands-on training on frontend tech.','Los Angeles','2025-07-01 10:00:00','2025-07-03 16:00:00','upcoming',2);

INSERT INTO Sessions()
VALUES
(1, 1, 'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(2, 1, 'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(3, 2, 'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(4, 3, 'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

INSERT INTO Registrations()
VALUES
(1, 1, 1, '2025-05-01'),
(2, 2, 1, '2025-05-02'),
(3, 3, 2, '2025-04-30'),
(4, 4, 2, '2025-04-28'),
(5, 5, 3, '2025-06-15');

INSERT INTO Feedback()
VALUES
(1, 3, 2, 4, 'Great insights!', '2025-05-16'),
(2, 4, 2, 5, 'Very informative.', '2025-05-16'),
(3, 2, 1, 3, 'Could be better.', '2025-06-11');

INSERT INTO Resources
(resource_id, event_id, resource_type, resource_url, uploaded_at)
VALUES
(1, 1, 'pdf',
'https://portal.com/resources/tech_meetup_agenda.pdf',
'2025-05-01 10:00:00'),
(2, 2, 'image',
'https://portal.com/resources/ai_poster.jpg',
'2025-04-20 09:00:00'),
(3, 3, 'link',
'https://portal.com/resources/html5_docs',
'2025-06-25 15:00:00');

-- Displaying the tables data
SELECT * FROM Users;
SELECT * FROM Events;
SELECT * FROM Sessions;
SELECT * FROM Registrations;
SELECT * FROM Feedback;
SELECT * FROM Resources;

-- Queries 
-- 1.Show a list of all upcoming events a user is registered for in their city, sorted by date. 
SELECT 
	u.user_id,
    u.full_name,
    e.event_id,
    e.title,
    e.city,
    e.start_date,
    e.status
FROM Users u
JOIN registrations r
	ON u.user_id=r.user_id
JOIN events e
	ON r.event_id=e.event_id
WHERE e.status='upcoming'
AND u.city=e.city
ORDER BY start_date;

-- Identify events with the highest average rating,considering only those that have received at least 10 feedback submissions. 
SELECT 
	f.event_id,
    AVG(f.rating) AS AVG_rating
FROM feedback f
GROUP BY f.event_id
HAVING COUNT(f.rating)>=10
ORDER BY AVG_rating DESC
LIMIT 1;

-- Retrieve users who have not registered for any events in the last 90 days.
SELECT 
	u.user_id,
    u.full_name,
    r.registration_date
FROM users u
LEFT JOIN registrations r
	ON u.user_id=r.user_id
	AND r.registration_date >= current_date()- INTERVAL 90 DAY
WHERE r.registration_date IS NULL;

-- Count how many sessions are scheduled between 10 AM to 12 PM for each event.
SELECT 
	COUNT(s.session_id) AS NO_of_Session,
    s.event_id AS eventID
FROM sessions s
WHERE TIME(s.start_time)>='10:00:00' AND TIME(s.end_time)<='12:00:00'
GROUP BY s.event_id;

-- List the top 5 cities with the highest number of distinct user registrations
SELECT  
	u.city AS city,
    COUNT(DISTINCT u.user_id) AS No_of_users
FROM users u
JOIN registrations r
	ON u.user_id=r.user_id
GROUP BY u.city
ORDER BY NO_of_users DESC,city ASC
LIMIT 5;

-- Generate a report showing the number of resources (PDFs, images, links) uploaded for each event.
SELECT 
	r.event_id,
    resource_type,
    Count(resource_type)
FROM resources r
GROUP BY r.event_id,resource_type;

-- List all users who gave feedback with a rating less than 3, along with their comments and associated event names. 
SELECT 
	u.user_id,
    u.full_name,
    e.title,
    f.rating,
    f.comments
FROM users u
JOIN feedback f ON f.user_id=u.user_id 
JOIN events e ON f.event_id=e.event_id
WHERE rating<3;


-- Display all upcoming events with the count of sessions scheduled for them. 
SELECT 
    e.event_id,
    e.title AS Event_Name,
    COUNT(s.session_id) AS NO_OF_SESSIONS,
    e.start_date AS Event_date
FROM events e
LEFT JOIN sessions s
	ON e.event_id=s.event_id
WHERE e.status='upcoming'
GROUP BY e.event_id;

-- For each event organizer, show the number of events created and their current status (upcoming, completed, cancelled). 
SELECT 
	e.organizer_id,
    COUNT(e.event_id) AS NoOFEventsConducted,
    e.status 
FROM events e
GROUP BY organizer_id,e.status;

-- Identify events that had registrations but received no feedback at all. 
SELECT DISTINCT
	e.event_id,
    e.title AS Event_Name
FROM events e
JOIN registrations r
	ON e.event_id=r.event_id
LEFT JOIN feedback f
	ON e.event_id=f.event_id
WHERE f.feedback_id IS NULL;

-- Find the number of users who registered each day in the last 7 days. 
SELECT 
	COUNT(DISTINCT r.user_id) AS users,
    registration_date
FROM registrations r
WHERE r.registration_date >= current_date()-interval 7 day
GROUP BY r.registration_date;

-- List the event(s) with the highest number of sessions. 
SELECT 
	s.event_id,
	COUNT(s.session_id) AS No_of_sessions
FROM sessions s
GROUP BY s.event_id
HAVING COUNT(s.session_id)=((
	SELECT MAX(t.count_session) 
	FROM (
		SELECT COUNT(session_id) AS count_session 
		from sessions  
		group by event_id
        ) t
	));
    
-- Calculate the average feedback rating of events conducted in each city.
SELECT 
e.city,
AVG(f.rating)
FROM events e
LEFT JOIN feedback f
	ON f.event_id=e.event_id
GROUP BY e.city;

-- List top 3 events based on the total number of user registrations.
SELECT 
	r.event_id,
    e.title,
	COUNT(r.registration_id) AS E_Registrations
FROM registrations r
JOIN events e
	ON e.event_id=r.event_id
GROUP BY r.event_id,e.title
ORDER BY E_Registrations DESC LIMIT 3;

-- Identify overlapping sessions within the same event (i.e., session start and end times that conflict).
SELECT 
	s1.session_id AS session1,
    s2.session_id AS session2,
    s1.event_id,
    s1.start_time,
    s1.end_time,
    s2.event_id ,
    s2.start_time,
    s2.end_time
FROM sessions s1
JOIN sessions s2
    ON s1.event_id=s2.event_id
    AND s1.session_id<s2.session_id
WHERE (s1.start_time < s2.end_time) AND (s2.start_time < s1.end_time);
;

-- Find users who created an account in the last 30 days but haven’t registered for any events.
SELECT 
	u.user_id,
    u.full_name,
	u.registration_date AS account_created_on
FROM users u
LEFT JOIN registrations r
	ON u.user_id=r.user_id
WHERE r.registration_id IS NULL
AND u.registration_date > DATE_SUB(CURRENT_DATE(), INTERVAL 30 day);
    
-- Identify speakers who are handling more than one session across all events. 
SELECT 
speaker_name,
COUNT(session_id) AS speaker_sessions
FROM sessions s
GROUP BY speaker_name
HAVING COUNT(session_id)>1;

-- List all events that do not have any resources uploaded. 
SELECT e.event_id,
r.resource_type
FROM events e
LEFT JOIN resources r
	ON e.event_id=r.event_id
    WHERE resource_type IS NULL;
    
-- For completed events, show total registrations and average feedback rating. 
SELECT 
	e.event_id,
    e.title,
    COUNT(r.registration_id) AS 'No of registrations',
    ROUND(AVG(f.rating),2) AS 'Event ratings'
FROM events e
LEFT JOIN registrations r 
	ON e.event_id=r.event_id
LEFT JOIN feedback f
	ON e.event_id=f.event_id
WHERE e.status='completed'
GROUP BY e.event_id,e.title;


-- For each user, calculate how many events they attended and how many feedbacks they submitted.
SELECT 
	u.user_id,
    u.full_name,
    COUNT(DISTINCT r.event_id) AS 'No of Events',
    COUNT(DISTINCT feedback_id) AS 'No of feebacks'
FROM users u
LEFT JOIN registrations r
	ON u.user_id=r.user_id 
LEFT JOIN feedback f
	ON f.user_id=u.user_id
GROUP BY u.user_id,u.full_name;

-- List top 5 users who have submitted the most feedback entries.
SELECT 
	u.user_id,
    u.full_name,
    COALESCE(f.feedback_id,0) AS 'No of feebacks'
FROM users u
LEFT JOIN (
	SELECT f.user_id,
    COUNT(f.feedback_id) as feedback_id
    from feedback f
    group by user_id) f
    ON f.user_id=u.user_id
ORDER BY f.feedback_id DESC
LIMIT 5;

-- Detect if a user has been registered more than once for the same event.
SELECT 
r1.user_id,
r1.event_id
FROM registrations r1 
JOIN registrations r2
ON r1.user_id=r2.user_id
AND r2.event_id=r1.event_id
AND r1.registration_id<r2.registration_id;

-- OR 

SELECT r.user_id,
r.event_id
FROM registrations r
GROUP BY r.user_id,r.event_id
HAVING COUNT(r.registration_id)>1;

-- Show a month-wise registration count trend over the past 12 months.
SELECT 
YEAR(registration_date) AS R_Year,
MONTH(registration_date) AS R_Month,
COUNT(registration_id) AS 'NO of registrations'
FROM registrations 
WHERE registration_date > DATE_SUB(current_date(),INTERVAL 12 MONTH)
GROUP BY R_Month,R_Year
ORDER BY R_Month,R_Year;

-- Compute the average duration (in minutes) of sessions in each event.
SELECT 
s.event_id,
AVG(TIMESTAMPDIFF(MINUTE,s.start_time,s.end_time)) AS avg_minutes
FROM sessions s
GROUP BY s.event_id;

-- List all events that currently have no sessions scheduled under them.
SELECT e.event_id
FROM events e 
LEFT JOIN sessions s
ON e.event_id=s.event_id
WHERE s.session_id IS NULL;