select * from Movie Natural Join Showing;
SELECT * FROM Movie NATURAL JOIN SHOWING 
WHERE Movie.title = 'Oceans Eleven' 
AND Showing.showtime = '11:00';

Select m.title, s.showing
From Movie m, Showing s
Where m.title = 'Oceans Eleven'
AND s.showing = '11:00';

SELECT tickets_Available FROM SHOWING WHERE movie_id = '321' AND showtime = '10:00';