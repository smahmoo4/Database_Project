/*drop table plays;
drop table writes;
drop table directs;
drop table acts;*/
drop table Role_;
drop table Tickets;
drop table Showing;
drop table Room;
drop table Writer;
drop table Actor;
drop table Director;
drop table Movie;
drop table Genre;
drop table Person;
drop table Members;
drop table Staff;
drop table Users;                                         /* EXAMPLE TUPLE OF AN ENTITY */ 

create table Users(                                  /* USER: [name_of_user, user_id, p_word] */
   name_of_user varchar(100) not null,
   user_id int not null,
   p_word varchar(250) not null,
   primary key (user_id)
);
      
create table Staff(                                 /* STAFF: [name_of_user, user_id, p_word, SSN] */
    name_of_user varchar(100) not null,
    user_id int not null,
    p_word varchar(250) not null,
    primary key (user_id),
    foreign key (user_id) references Users
);
      
create table Members(                               /* MEMBERS: [name_of_user, user_id, p_word] */
    name_of_user varchar(100) not null,
    user_id int not null,     
    p_word varchar(250) not null,
    primary key (user_id),
    foreign key (user_id) references Users
);

--add person_id as PK
create table Person( /* PERSON: [person_name, date_of_birth, gender] */
   person_id int not null,
   person_name varchar(50) not null,
   date_of_birth varchar(50) not null,
   gender varchar(6) not null,
   primary key(person_id)
);

create table Genre(                                 /* GENRE: [category] */
  genre varchar(20),
  primary key (genre)
);

create table Movie(                                 /* MOVIE: [movie_id, title, genre, release_year, rating, length] */
   movie_id int not null,
   title varchar(100) not null,
   genre varchar(50),
   release_year int,
   rating varchar(5) not null,
   length int,
   primary key (movie_id),
   foreign key (genre) references Genre
);
      
create table Director(                              /* DIRECTOR: [director_id, person_name, date_of birth, movie_id] */
    person_id int not null,
    person_name varchar(20) not null,
    date_of_birth varchar(50) not null,
    movie_id int not null,
    primary key (person_id),
    foreign key (movie_id) references Movie
);
      
create table Actor(                                 /* ACTOR: [actor_id, person_name, date_of birth, movie_id] */
    person_id int not null,
    person_name varchar(20) not null,
    date_of_birth varchar(50) not null, 
    movie_id int not null,
    primary key (person_id),
    foreign key (movie_id) references Movie
);

create table Writer(                                /* WRITER: [writer_id, person_name, date_of birth, movie_id] */
    person_id int not null,
    person_name varchar(20) not null,
    date_of_birth varchar(50) not null, 
    movie_id int not null,
    primary key (person_id),
    foreign key (movie_id) references Movie
);

create table Room(                                  /* ROOM: [room_number, capacity] */
    room_number int not null,
    capacity int not null,
    primary key (room_number)
);

-- add tickets Available
create table Showing(                               /* SHOWING: [show_id, movie_id, showtime, room_number] */
    show_id int,
    tickets_Available int not null,
    movie_id int not null,
    showtime varchar(5),  
    room_number int not null,
    primary key (show_id),
    foreign key (room_number) references Room,
    foreign key (movie_id) references Movie
);


--add no.of.ticketsBooked
create table Tickets(                               /* TICKETS: [ticket_id, user_id show_id, price] */ 
    ticket_id int not null,
    user_id int not null,
    show_id int not null,
    tickets_Booked int, 
    price int not null,
    primary key (ticket_id),
    foreign key (show_id) references Showing,
    foreign key (user_id) references Users
);

create table Role_(                                 /* ROLE: [role_id, actor_id, char_name] */
    role_id int not null,
    actor_id int not null,
    char_name varchar(100) not null,
    primary key (role_id, actor_id),
    foreign key (actor_id) references Actor 
);

/*create table acts(                                  
    acts_id int not null,
    movie_id int not null, 
    actor_id int not null,
    primary key (acts_id),
    foreign key (movie_id) references Movie,
    foreign key (actor_id) references Actor
);

create table directs(                               
    directs_id int not null,
    movie_id int not null, 
    actor_id int not null,
    primary key (directs_id),
    foreign key (movie_id) references Movie,
    foreign key (actor_id) references Actor
);

create table writes(                                
  writes_id int not null,
  movie_id int not null, 
  actor_id int not null,
  primary key (writes_id),
  foreign key (movie_id) references Movie,
  foreign key (actor_id) references Actor
);

create table plays(                                 
  room_number int not null,
  title varchar(100) not null,
  movie_id int not null,
  foreign key (room_number) references Room,
  foreign key (movie_id) references Movie,
  primary key (room_number)
);*/