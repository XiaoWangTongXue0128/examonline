/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2022/1/6 ������ ���� 6:55:37                      */
/*==============================================================*/


drop table if exists t_dictionary;

drop table if exists t_exam;

drop table if exists t_question;

drop table if exists t_student;

drop table if exists t_student_exam;

drop table if exists t_teacher;

drop table if exists t_template;

drop table if exists t_template_teacher;

/*==============================================================*/
/* Table: t_dictionary                                          */
/*==============================================================*/
create table t_dictionary
(
   id                   bigint not null auto_increment,
   name                 varchar(32),
   type                 varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: t_exam                                                */
/*==============================================================*/
create table t_exam
(
   id                   bigint not null auto_increment,
   name                 varchar(128),
   course               varchar(32),
   template_id          bigint,
   tid                  bigint,
   start_time           datetime,
   end_time             datetime,
   duration             int comment '��λ������',
   status               varchar(32) comment '������δ�������ѷ����������У�����ɣ�������',
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: t_question                                            */
/*==============================================================*/
create table t_question
(
   id                   bigint not null auto_increment,
   type                 varchar(32) comment '��ѡ�⣬��ѡ�⣬�ж��⣬����⣬�ۺ���',
   course               varchar(32) comment 'ֱ�Ӵ洢�γ����ƣ����÷�',
   level                varchar(32) comment '�򵥣��еȣ�����',
   subject              text,
   options              text,
   answer               text,
   status               varchar(32) comment '������˽�У����У�������',
   tid                  bigint comment '����teacher.id',
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: t_student                                             */
/*==============================================================*/
create table t_student
(
   id                   bigint not null auto_increment,
   code                 varchar(32),
   sname                varchar(32),
   mnemonic_code        varchar(32),
   pass                 varchar(64),
   grade                int comment '2021,2022',
   major                varchar(32) comment 'ֱ�Ӵ洢רҵ���ƣ�������',
   class_no             varchar(32) comment '1��',
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (id),
   unique key AK_Key_2 (code),
   unique key AK_Key_3 (sname),
   unique key AK_Key_4 (mnemonic_code)
);

/*==============================================================*/
/* Table: t_student_exam                                        */
/*==============================================================*/
create table t_student_exam
(
   exam_id              bigint not null,
   student_id           bigint not null,
   exam_group           varchar(32) comment '�Զ���༶���ƣ����ް�',
   status               varchar(32) comment '������δ���ԣ������У�����ɣ������ף�ȱ��',
   start_time           datetime,
   end_time             datetime,
   answer1              text,
   answer2              text,
   answer3              text,
   answer4              text,
   answer5              text,
   review4              text,
   review5              text,
   score                int,
   page_path            varchar(128),
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (exam_id, student_id)
);

/*==============================================================*/
/* Table: t_teacher                                             */
/*==============================================================*/
create table t_teacher
(
   id                   bigint not null auto_increment,
   tname                varchar(32),
   mnemonic_code        varchar(32),
   pass                 varchar(64),
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (id),
   unique key AK_Key_2 (tname),
   unique key AK_Key_3 (mnemonic_code)
);

/*==============================================================*/
/* Table: t_template                                            */
/*==============================================================*/
create table t_template
(
   id                   bigint not null auto_increment,
   name                 varchar(32),
   type                 varchar(32) comment '��̬ģ�壬��̬ģ��',
   question1            text,
   question2            text,
   question3            text,
   question4            text,
   question5            text,
   total_score          int,
   status               varchar(32) comment '������˽�У����У�������',
   tid                  bigint comment '����teacher.id',
   yl1                  varchar(32),
   yl2                  varchar(32),
   yl3                  varchar(32),
   yl4                  varchar(32),
   create_time          datetime,
   update_time          datetime,
   primary key (id),
   unique key AK_Key_2 (name)
);

/*==============================================================*/
/* Table: t_template_teacher                                    */
/*==============================================================*/
create table t_template_teacher
(
   template_id          bigint not null,
   teacher_id           bigint not null,
   primary key (template_id, teacher_id)
);

