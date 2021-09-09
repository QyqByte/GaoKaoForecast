
-- 学校表格
    create table schools(
       id int primary key auto_increment,
       school_id int not null unique ,
       school_desc varchar(50) null,
       school_name varchar(20) not null unique
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

    --  学科表格，每个学校都要有自己的学科专业；
    drop table if exists majors;
    create table majors(
        id int primary key auto_increment,
        major_name varchar(100) not null,
        major_batch varchar(20) null,
        major_min_score int null,
        major_year int null,
        major_min_section int null,
        major_school_id int not null,
        major_province int null,
        major_average varchar(10) default '-'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

    -- 历年录取分数线表格
    drop table if exists scorebatch;
    create table scorebatch(
        id int primary key auto_increment,
        batchLiFirst int null,
        batchLiSecond int null,
        batchWenFirst int null,
        batchWenSecond int null,
        year int not null
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

    insert into scorebatch(batchlifirst, batchlisecond, batchwenfirst, batchwensecond,year) values(544,418,556,465,2020);
    insert into scorebatch(batchlifirst, batchlisecond, batchwenfirst, batchwensecond,year) values(502,385,536,447,2019);

    -- user用户表格
    drop table if exists users;
    create table users(
        id int primary key auto_increment,
        user_name varchar(20) not null,
        user_email varchar(50) null,
        user_password varchar(50) not null,
        user_rank int null,
        user_score int null
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

    insert into users(user_name, user_email, user_password, user_rank, user_score) values ('admin','admin@163.com','admin',0,0);