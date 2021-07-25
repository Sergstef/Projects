/* Processed by ecpg (13.3) */
/* These include files are added by the preprocessor */
#include <ecpglib.h>
#include <ecpgerrno.h>
#include <sqlca.h>
/* End of automatic include section */

#line 1 "db.pgc"
#include <iostream>
#include <string>

using namespace std;


#line 1 "C:/Program Files/PostgreSQL/13/include/sqlca.h"
#ifndef POSTGRES_SQLCA_H
#define POSTGRES_SQLCA_H

#ifndef PGDLLIMPORT
#if  defined(WIN32) || defined(__CYGWIN__)
#define PGDLLIMPORT __declspec (dllimport)
#else
#define PGDLLIMPORT
#endif							/* __CYGWIN__ */
#endif							/* PGDLLIMPORT */

#define SQLERRMC_LEN	150

#ifdef __cplusplus
extern "C"
{
#endif

struct sqlca_t
{
	char		sqlcaid[8];
	long		sqlabc;
	long		sqlcode;
	struct
	{
		int			sqlerrml;
		char		sqlerrmc[SQLERRMC_LEN];
	}			sqlerrm;
	char		sqlerrp[8];
	long		sqlerrd[6];
	/* Element 0: empty						*/
	/* 1: OID of processed tuple if applicable			*/
	/* 2: number of rows processed				*/
	/* after an INSERT, UPDATE or				*/
	/* DELETE statement					*/
	/* 3: empty						*/
	/* 4: empty						*/
	/* 5: empty						*/
	char		sqlwarn[8];
	/* Element 0: set to 'W' if at least one other is 'W'	*/
	/* 1: if 'W' at least one character string		*/
	/* value was truncated when it was			*/
	/* stored into a host variable.             */

	/*
	 * 2: if 'W' a (hopefully) non-fatal notice occurred
	 */	/* 3: empty */
	/* 4: empty						*/
	/* 5: empty						*/
	/* 6: empty						*/
	/* 7: empty						*/

	char		sqlstate[5];
};

struct sqlca_t *ECPGget_sqlca(void);

#ifndef POSTGRES_ECPG_INTERNAL
#define sqlca (*ECPGget_sqlca())
#endif

#ifdef __cplusplus
}
#endif

#endif

#line 6 "db.pgc"


#line 1 "C:/Program Files/PostgreSQL/13/include/pgtypes_date.h"
/* src/interfaces/ecpg/include/pgtypes_date.h */

#ifndef PGTYPES_DATETIME
#define PGTYPES_DATETIME

#include <pgtypes.h>
#include <pgtypes_timestamp.h>

typedef long date;

#ifdef __cplusplus
extern "C"
{
#endif

extern date * PGTYPESdate_new(void);
extern void PGTYPESdate_free(date *);
extern date PGTYPESdate_from_asc(char *, char **);
extern char *PGTYPESdate_to_asc(date);
extern date PGTYPESdate_from_timestamp(timestamp);
extern void PGTYPESdate_julmdy(date, int *);
extern void PGTYPESdate_mdyjul(int *, date *);
extern int	PGTYPESdate_dayofweek(date);
extern void PGTYPESdate_today(date *);
extern int	PGTYPESdate_defmt_asc(date *, const char *, const char *);
extern int	PGTYPESdate_fmt_asc(date, const char *, char *);

#ifdef __cplusplus
}
#endif

#endif							/* PGTYPES_DATETIME */

#line 7 "db.pgc"


int create_database(){
    { ECPGsetcommit(__LINE__, "on", NULL);}
#line 10 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create database newdatabase", ECPGt_EOIT, ECPGt_EORT);}
#line 11 "db.pgc"

    { ECPGsetcommit(__LINE__, "off", NULL);}
#line 12 "db.pgc"

    cout << "Database was created" << endl;
	return 1;
}

void create_functions(){
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function addDataToCars ( mark text , owner text , sale integer ) returns void as $$\
	begin\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values (mark, owner, sale);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 22 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 23 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function addDataToDetails ( detail text , seller text , cost integer , maxdetails integer ) returns void as $$\
	begin\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values (detail, seller, cost, maxDetails);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 29 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 30 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function addDataToFix ( car text , monthd text , garage integer , details integer , numberdet integer , totalcost integer ) returns void as $$\
	begin\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (car, monthD, garage, details, numberDet, totalCost);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 36 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 37 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function addDataToGarage ( number text , place text , money integer ) returns void as $$\
	begin\
		insert into гараж (номер, расположение, \"комиссионные, %\") values (number, place, money);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 43 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 44 "db.pgc"


	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function selectSomething ( ) returns setof автомобиль as $$\
	begin\
		return query\
		select идентификатор, марка, \"атп-владелец\", \"скидка, %\" from автомобиль\
		where марка = 'Газ-52';\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 52 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 53 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function createTables ( ) returns void as $$\
	begin\
		CREATE TABLE автомобиль (\
		идентификатор int primary key generated always as identity,\
		марка varchar(100) not null,\
		\"атп-владелец\" varchar(20) not null,\
		\"скидка, %\" int not null);\
		CREATE TABLE гараж (\
		идентификатор int primary key generated always as identity,\
		номер varchar(100) not null,\
		расположение varchar(50) not null,\
		\"комиссионные, %\" int not null);\
		CREATE TABLE детали (\
		идентификатор int primary key generated always as identity,\
		деталь varchar(50) not null,\
		продавец varchar(50) not null,\
		\"стоимость, руб\" int not null,\
		\"макс. кол-во\" int not null);\
		CREATE TABLE ремонт (\
		\"номер заказа\" int primary key generated always as identity,\
		автомобиль varchar(10) not null,\
		дата varchar(20) not null,\
		гараж int not null,\
		детали int not null,\
		\"кол-во\" int not null,\
		\"общая стоимость, руб\" int not null);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 82 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 83 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function clearTable ( tablename text ) returns void as $$\
	begin\
		execute format('truncate table %s', tableName);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 89 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 90 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function createAdmin ( namet text , passwordt text ) returns void as $$\
	begin\
		execute format('create user %s with password ''%s''', nameT, passwordT);\
		execute format('ALTER USER %s WITH SUPERUSER CREATEDB CREATEROLE LOGIN INHERIT REPLICATION', nameT);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 97 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 98 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function createDatabase ( dbname text ) returns void as $$\
	begin\
		execute format('create database %s', dbName);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 104 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 105 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function createUser ( namet text , passwordt text ) returns void as $$\
	begin\
		execute format('create user %s with password ''%s''', nameT, passwordT);\
		execute format('grant select on all tables in schema public to %s', nameT);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 112 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 113 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function deleteData ( ) returns void as $$\
	begin\
		delete from автомобиль where марка = 'Газ-24';\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 119 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 120 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function deleteDatabase ( dbname character varying ) returns void as $$\
	begin\
		execute format('drop database %s', dbName);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 126 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 127 "db.pgc"

	
	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create function insertData ( ) returns void as $$\
	begin\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values ('Газ-24', 'АТП1', 4);\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values ('Газ-52', 'АТП1', 0);\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values ('Зил-130', 'АТП3', 3);\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values ('Зил-133', 'АТП4', 5);\
		insert into автомобиль (марка, \"атп-владелец\", \"скидка, %\") values ('Газ-1222', 'АТП5', 4);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N1', 'АТП1', 3);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N2', 'АТП1', 3);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N1', 'АТП2', 4);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N3', 'АТП2', 4);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N4', 'АТП4', 4);\
		insert into гараж (номер, расположение, \"комиссионные, %\") values ('N5', 'АТП5', 3);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Трубка', 'АТП1', 10000, 100);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Скоба', 'АТП1', 5000, 230);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Картер', 'АТП3', 40000, 70);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Штуцер', 'АТП2', 7000, 200);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Прокладка', 'АТП2', 5000, 1200);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Пробка', 'АТП1', 5000, 300);\
		insert into детали (деталь, продавец, \"стоимость, руб\", \"макс. кол-во\") values ('Толкатель', 'АТП1', 11000, 120);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (4, 'Январь', 3, 7, 7, 77000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Февраль', 3, 2, 4, 20000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Февраль', 5, 4, 1, 7000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Март', 6, 5, 6, 30000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (2, 'Апрель', 6, 7, 9, 99000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (4, 'Апрель', 6, 6, 8, 40000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (1, 'Май', 5, 7, 3, 33000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (1, 'Май', 3, 3, 2, 80000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Май', 6, 1, 16, 160000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Май', 5, 5, 21, 105000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (2, 'Июнь', 1, 1, 5, 50000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (5, 'Июнь', 6, 2, 3, 15000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (3, 'Август', 2, 6, 6, 30000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (4, 'Август', 5, 1, 4, 40000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (4, 'Август', 1, 7, 7, 77000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (5, 'Август', 1, 6, 1, 5000);\
		insert into ремонт (автомобиль, дата, гараж, детали, \"кол-во\", \"общая стоимость, руб\") values (2, 'Август', 4, 2, 1, 5000);\
	end\
	$$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 167 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 168 "db.pgc"

}

int create_conn(const char *t, const char *u, const char *p){
    /* exec sql begin declare section */
     
        
        
        
    
#line 173 "db.pgc"
 char dbname [ 1024 ] ;
 
#line 174 "db.pgc"
 const char * target = t ;
 
#line 175 "db.pgc"
 const char * user = u ;
 
#line 176 "db.pgc"
 const char * passwd = p ;
/* exec sql end declare section */
#line 177 "db.pgc"

    { ECPGconnect(__LINE__, 0, target , user , passwd , "con", 0); }
#line 178 "db.pgc"

    if(sqlca.sqlcode!=0){
            return 0;
        }
    { ECPGtrans(__LINE__, NULL, "commit");}
#line 182 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select current_database ( )", ECPGt_EOIT, 
	ECPGt_char,(dbname),(long)1024,(long)1,(1024)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EORT);}
#line 183 "db.pgc"

    cout << "Current database - " << dbname << endl;
    return 1;
}

int create_role(string role, const char *name, const char *pass){
	/* exec sql begin declare section */
        
        
    
#line 190 "db.pgc"
 const char * n = name ;
 
#line 191 "db.pgc"
 const char * p = pass ;
/* exec sql end declare section */
#line 192 "db.pgc"

    if (role == "admin") {
		{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select createadmin ( $1  , $2  )", 
	ECPGt_char,&(n),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(p),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 194 "db.pgc"

		{ ECPGtrans(__LINE__, NULL, "commit");}
#line 195 "db.pgc"

		cout << "Admin was created" << endl;
	}
	else {
		{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select createuser ( $1  , $2  )", 
	ECPGt_char,&(n),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(p),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 199 "db.pgc"

		{ ECPGtrans(__LINE__, NULL, "commit");}
#line 200 "db.pgc"

		cout << "User was created" << endl;
	}
    return 1;

}

void cr_tables(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select createtables ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 208 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 209 "db.pgc"

    cout << "Таблица создана\n";
}

void addDataToCars(const char *mark, const char *owner, int sale) {
	/* exec sql begin declare section */
        
        
	   
    
#line 215 "db.pgc"
 const char * m = mark ;
 
#line 216 "db.pgc"
 const char * o = owner ;
 
#line 217 "db.pgc"
 int s = sale ;
/* exec sql end declare section */
#line 218 "db.pgc"

	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select addDataToCars ( $1  , $2  , $3  )", 
	ECPGt_char,&(m),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(o),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(s),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 219 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 220 "db.pgc"

	cout << "Data added to cars" << endl;
}

void addDataToDetails(const char *detail, const char *seller, int cost, int maxdetails) {
	/* exec sql begin declare section */
        
        
	   
	   
    
#line 226 "db.pgc"
 const char * d = detail ;
 
#line 227 "db.pgc"
 const char * s = seller ;
 
#line 228 "db.pgc"
 int c = cost ;
 
#line 229 "db.pgc"
 int m = maxdetails ;
/* exec sql end declare section */
#line 230 "db.pgc"

	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select addDataToDetails ( $1  , $2  , $3  , $4  )", 
	ECPGt_char,&(d),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(s),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(c),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(m),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 231 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 232 "db.pgc"

	cout << "Data added to details" << endl;
}

void addDataToFix(const char *car, const char *monthd, int garage, int details, int numberdet, int totalcost) {
	/* exec sql begin declare section */
        
        
	   
	   
	   
	   
    
#line 238 "db.pgc"
 const char * c = car ;
 
#line 239 "db.pgc"
 const char * m = monthd ;
 
#line 240 "db.pgc"
 int g = garage ;
 
#line 241 "db.pgc"
 int d = details ;
 
#line 242 "db.pgc"
 int n = numberdet ;
 
#line 243 "db.pgc"
 int t = totalcost ;
/* exec sql end declare section */
#line 244 "db.pgc"

	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select addDataToFix ( $1  , $2  , $3  , $4  , $5  , $6  )", 
	ECPGt_char,&(c),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(m),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(g),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(d),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(n),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(t),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 245 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 246 "db.pgc"

	cout << "Data added to fix" << endl;
}

void addDataToGarage(const char *number, const char *place, int money) {
	/* exec sql begin declare section */
        
        
	   
    
#line 252 "db.pgc"
 const char * n = number ;
 
#line 253 "db.pgc"
 const char * p = place ;
 
#line 254 "db.pgc"
 int m = money ;
/* exec sql end declare section */
#line 255 "db.pgc"

	{ ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select addDataToGarage ( $1  , $2  , $3  )", 
	ECPGt_char,&(n),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(p),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(m),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 256 "db.pgc"

	{ ECPGtrans(__LINE__, NULL, "commit");}
#line 257 "db.pgc"

	cout << "Data added to garage" << endl;
}

void clear_table(const char *name){
	/* exec sql begin declare section */
        
    
#line 263 "db.pgc"
 const char * n = name ;
/* exec sql end declare section */
#line 264 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select cleartable ( $1  )", 
	ECPGt_char,&(n),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 265 "db.pgc"

	if (sqlca.sqlcode != 0){
        cout << sqlca.sqlcode << endl;
        }
    { ECPGtrans(__LINE__, NULL, "commit");}
#line 269 "db.pgc"

    cout << "Table cleared" << endl;
}

void create_tables(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select createtables ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 274 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 275 "db.pgc"

    cout << "Tables created" << endl;
}

void deleteData(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select deletedata ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 280 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 281 "db.pgc"

    cout << "Data deleted from cars with mark Gaz" << endl;
}

void insertData(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "select insertdata ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 286 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 287 "db.pgc"

    cout << "Data inserted" << endl;
}

void selectSomething(){
    /* exec sql begin declare section */
     
     
     
	 
    
#line 293 "db.pgc"
 int id ;
 
#line 294 "db.pgc"
  struct varchar_1  { int len; char arr[ 32 ]; }  m ;
 
#line 295 "db.pgc"
  struct varchar_2  { int len; char arr[ 32 ]; }  o ;
 
#line 296 "db.pgc"
 int sale ;
/* exec sql end declare section */
#line 297 "db.pgc"


    /* declare search_res cursor for select * from selectsomething ( ) */
#line 299 "db.pgc"


    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "declare search_res cursor for select * from selectsomething ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 301 "db.pgc"

    while(1){
        { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "fetch search_res", ECPGt_EOIT, 
	ECPGt_int,&(id),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_varchar,&(m),(long)32,(long)1,sizeof(struct varchar_1), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_varchar,&(o),(long)32,(long)1,sizeof(struct varchar_2), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_int,&(sale),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EORT);}
#line 303 "db.pgc"

        if(sqlca.sqlcode!=0){
			cout << sqlca.sqlcode << endl;
            break;
        }
        printf("id=%d, mark=%s, owner=%s, sale=%d\n", id, m.arr, o.arr, sale);
    }
    
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "close search_res", ECPGt_EOIT, ECPGt_EORT);}
#line 311 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 312 "db.pgc"

}

void deleteDatabase(){
    { ECPGsetcommit(__LINE__, "on", NULL);}
#line 316 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "drop database newdatabase", ECPGt_EOIT, ECPGt_EORT);}
#line 317 "db.pgc"

    { ECPGsetcommit(__LINE__, "off", NULL);}
#line 318 "db.pgc"

    cout << "Database deleted" << endl;
}

void roles_proc(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure create_start_guest ( ) as $$\
        begin\
            create role guest_ password '1' login;\
            grant select on table grocery to guest_;\
        end;\
    $$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 328 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 329 "db.pgc"


    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure create_guest ( ) as $$\
        begin\
            create role new_guest password '1' login;\
            grant select on table grocery to new_guest;\
        end;\
    $$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 336 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 337 "db.pgc"


        { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure create_admin ( ) as $$\
        begin\
            create role new_admin password '1' superuser;\
        end;\
    $$ language plpgsql", ECPGt_EOIT, ECPGt_EORT);}
#line 343 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 344 "db.pgc"

}

void fill_table(){
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create procedure fill_table ( ) as $$\
	INSERT INTO grocery VALUES\
    (001, 'Холодильник', true),\
    (002, 'Стелаж', false),\
    (003, 'Склад', false)\
    $$ language sql", ECPGt_EOIT, ECPGt_EORT);}
#line 353 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call fill_table ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 354 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 355 "db.pgc"

    cout << "Таблица заполнена\n";
}

int ins_row(){
    cout << "Введите данные новой строки\n";
    cout << "ID - int:\n";
    int i;
    cin >> i;
    if (cin.fail()){
        cout << "Неверный ввод ID\n";
        return 0;
    }
        
    cout << "Местоположение товара - String:\n";
    string p;
    cin >> p;
    if (cin.fail()){
        cout << "Неверный ввод местоположения\n";
        return 0;
    }

    cout << "Не утилизирован ли товар - boolean:\n";
    bool r;
    cin >> r;
    if (cin.fail()){
        cout << "Неверный ввод утилизированности товара\n";
        return 0;
    }

    /* exec sql begin declare section */
       
        
       
    
#line 386 "db.pgc"
 int id = i ;
 
#line 387 "db.pgc"
 const char * place = p . c_str () ;
 
#line 388 "db.pgc"
 bool rem = r ;
/* exec sql end declare section */
#line 389 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call insert_row ( $1  , $2  , $3  )", 
	ECPGt_int,&(id),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(place),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_bool,&(rem),(long)1,(long)1,sizeof(bool), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 390 "db.pgc"

    if (sqlca.sqlcode != 0){
        cout << "Невозможно добавить указанную строку (повторение первичного ключа)\n";
        return 0;
    }
    { ECPGtrans(__LINE__, NULL, "commit");}
#line 395 "db.pgc"


    cout << "Поле упешно добавлено\n";
    return 1;
}

void drop_db(){
    { ECPGdisconnect(__LINE__, "ALL");}
#line 402 "db.pgc"

    create_conn("TASK_HSE@localhost:5432", "postgres", "8850");
    { ECPGsetcommit(__LINE__, "on", NULL);}
#line 404 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "drop database grocery_shop_cpp", ECPGt_EOIT, ECPGt_EORT);}
#line 405 "db.pgc"

    { ECPGsetcommit(__LINE__, "off", NULL);}
#line 406 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure drop_role_ ( ) as $$\
        drop role guest_;\
    $$ language sql", ECPGt_EOIT, ECPGt_EORT);}
#line 409 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure drop_role_new_guest ( ) as $$\
        drop role new_guest;\
    $$ language sql", ECPGt_EOIT, ECPGt_EORT);}
#line 412 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "create or replace procedure drop_role_new_admin ( ) as $$\
        drop role new_admin;\
    $$ language sql", ECPGt_EOIT, ECPGt_EORT);}
#line 415 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call drop_role_ ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 416 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 417 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call drop_role_new_guest ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 418 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 419 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call drop_role_new_admin ( )", ECPGt_EOIT, ECPGt_EORT);}
#line 420 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 421 "db.pgc"

    cout << "База данных удалена\n";
    { ECPGdisconnect(__LINE__, "ALL");}
#line 423 "db.pgc"

}

int update_row(){
    cout << "Введите ID, по которому обновляеются данные:\n";
    int i;
    cin >> i;
    if (cin.fail()){
        cout << "Неверный ввод ID\n";
        return 0;
    }
        
    cout << "Введите новое местоположение товара:\n";
    string p;
    cin >> p;
    if (cin.fail()){
        cout << "Неверный ввод местоположения\n";
        return 0;
    }

    cout << "Введите новые данные об утилизованности товара:\n";
    bool r;
    cin >> r;
    if (cin.fail()){
        cout << "Неверный ввод утилизированности товара\n";
        return 0;
    }

    /* exec sql begin declare section */
       
        
       
    
#line 452 "db.pgc"
 int id = i ;
 
#line 453 "db.pgc"
 const char * place = p . c_str () ;
 
#line 454 "db.pgc"
 bool rem = r ;
/* exec sql end declare section */
#line 455 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call update_row ( $1  , $2  , $3  )", 
	ECPGt_int,&(id),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_char,&(place),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_bool,&(rem),(long)1,(long)1,sizeof(bool), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 456 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 457 "db.pgc"

    cout << "Строка изменена\n";
    return 1;
}

int delete_rows(){
    cout << "Введите местоположение, по которому будет производиться удаление:\n";
    string p;
    cin >> p;
    if (cin.fail()){
        cout << "Неверный ввод местоположения\n";
        return 0;
    }

    /* exec sql begin declare section */
        
    
#line 472 "db.pgc"
 const char * place = p . c_str () ;
/* exec sql end declare section */
#line 473 "db.pgc"

    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "call delete_rows ( $1  )", 
	ECPGt_char,&(place),(long)0,(long)1,(1)*sizeof(char), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EOIT, ECPGt_EORT);}
#line 474 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 475 "db.pgc"

    cout << "Соответствующие строки удалены\n";
    return 1;
}

void show_table(){
    /* exec sql begin declare section */
     
     
     
    
#line 482 "db.pgc"
 int id ;
 
#line 483 "db.pgc"
  struct varchar_3  { int len; char arr[ 32 ]; }  pp ;
 
#line 484 "db.pgc"
  struct varchar_4  { int len; char arr[ 32 ]; }  rem ;
/* exec sql end declare section */
#line 485 "db.pgc"


    /* declare full_t cursor for select * from grocery */
#line 487 "db.pgc"


    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "declare full_t cursor for select * from grocery", ECPGt_EOIT, ECPGt_EORT);}
#line 489 "db.pgc"

    while(1){
        { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "fetch full_t", ECPGt_EOIT, 
	ECPGt_int,&(id),(long)1,(long)1,sizeof(int), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_varchar,&(pp),(long)32,(long)1,sizeof(struct varchar_3), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, 
	ECPGt_varchar,&(rem),(long)32,(long)1,sizeof(struct varchar_4), 
	ECPGt_NO_INDICATOR, NULL , 0L, 0L, 0L, ECPGt_EORT);}
#line 491 "db.pgc"

        if(sqlca.sqlcode!=0){
            break;
        }
        printf("id=%d, productplace=%s, isremoved=%s\n", id, pp.arr, rem.arr);
    }
    
    { ECPGdo(__LINE__, 0, 1, NULL, 0, ECPGst_normal, "close full_t", ECPGt_EOIT, ECPGt_EORT);}
#line 498 "db.pgc"

    { ECPGtrans(__LINE__, NULL, "commit");}
#line 499 "db.pgc"

}

int main() {
	cout << "Select:" << endl;
	cout << "1. Enter" << endl;
	cout << "2. Register" << endl;
	int sel;
	cin >> sel;
	while (sel != 1 && sel != 2){
        cout << "Try again" << endl;
        cin >> sel;
    }
	string password;
	string name;
		switch(sel) {
			case 1: {
					cout << "Enter adress and database:" << endl;
					string adress;
					cin >> adress;
					const char *t = adress.c_str();
					cout << "Enter type of user(admin or user):" << endl;
					string type;
					cin >> type;
					cout << "Enter name of user:" << endl;
					cin >> name;
					const char *n = name.c_str();
					cout << "Enter password:" << endl;
					cin >> password;
					const char *p = password.c_str();
					cout << "Database connection..." << endl;
					create_conn(t, n, p);
					if(type == "admin") {
						while(1) {
							cout << "Select option" << endl;
							cout << "1. Create database" << endl;
							cout << "2. Add data to cars table" << endl;
							cout << "3. Add data to details table" << endl;
							cout << "4. Add data to fix table" << endl;
							cout << "5. Add data to garage table" << endl;
							cout << "6. Clear table" << endl;
							cout << "7. Create user" << endl;
							cout << "8. Create tables" << endl;
							cout << "9. Insert data to tables" << endl;
							cout << "10. Delete data from table" << endl;
							cout << "11. Delete database" << endl;
							cout << "12. Select data" << endl;
							cout << "13. Create functions" << endl;
							cout << "14. Exit" << endl;
							int option;
							cin >> option;
							switch (option) {
								case 1: {
									create_database();
									break;
								}
								case 2: {
									cout << "Enter mark:" << endl;
									string mark;
									cin >> mark;
									const char *m = mark.c_str();
									cout << "Enter owner:" << endl;
									string owner;
									cin >> owner;
									const char *o = owner.c_str();
									cout << "Enter sale:" << endl;
									int sale;
									cin >> sale;
									addDataToCars(m, o, sale);
									break;
								}
								case 3: {
									cout << "Enter detail:" << endl;
									string detail;
									cin >> detail;
									const char *d = detail.c_str();
									cout << "Enter seller:" << endl;
									string seller;
									cin >> seller;
									const char *s = seller.c_str();
									cout << "Enter cost:" << endl;
									int cost;
									cin >> cost;
									cout << "Enter maxdetails:" << endl;
									int maxdetails;
									cin >> maxdetails;
									addDataToDetails(d, s, cost, maxdetails);
									break;
								}
								case 4: {
									cout << "Enter car:" << endl;
									string car;
									cin >> car;
									const char *c = car.c_str();
									cout << "Enter month:" << endl;
									string month;
									cin >> month;
									const char *m = month.c_str();
									cout << "Enter garage:" << endl;
									int garage;
									cin >> garage;
									cout << "Enter details:" << endl;
									int details;
									cin >> details;
									cout << "Enter number:" << endl;
									int number;
									cin >> number;
									cout << "Enter totalcost:" << endl;
									int totalcost;
									cin >> totalcost;
									addDataToFix(c, m, garage, details, number, totalcost);
									break;
								}
								case 5: {
									cout << "Enter number:" << endl;
									string number;
									cin >> number;
									const char *n = number.c_str();
									cout << "Enter place:" << endl;
									string place;
									cin >> place;
									const char *p = place.c_str();
									cout << "Enter money:" << endl;
									int money;
									cin >> money;
									addDataToGarage(n, p, money);
									break;
								}
								case 6: {
									cout << "Enter name:" << endl;
									string name;
									cin >> name;
									const char *n = name.c_str();
									clear_table(n);
									break;
								}
								case 7: {
									cout << "Enter your role(admin or user)" << endl;
									string role;
									cin >> role;
									const char *r = role.c_str();
									cout << "Enter name:" << endl;
									cin >> name;
									const char *n = name.c_str();
									cout << "Enter password:" << endl;
									cin >> password;
									const char *p = password.c_str();
									create_role(r, n, p);
									break;
								}
								case 8: {
									create_tables();
									break;
								}
								case 9: {
									insertData();
									break;
								}
								case 10: {
									deleteData();
									break;
								}
								case 11: {
									deleteDatabase();
									break;
								}
								case 12: {
									selectSomething();
									break;
								}
								case 13: {
									{ ECPGdisconnect(__LINE__, "ALL");}
#line 670 "db.pgc"

									create_conn("newdatabase@localhost:5432", n, p);
									create_functions();
									{ ECPGdisconnect(__LINE__, "ALL");}
#line 673 "db.pgc"

									create_conn(t, n, p);
									break;
								}
								case 14: {
									{ ECPGdisconnect(__LINE__, "ALL");}
#line 678 "db.pgc"

									return 0;
								}
							}
						}
					}
					else {
						while(1) {
							cout << "Select option" << endl;
							cout << "1. Select data" << endl;
							cout << "2. Exit" << endl;
							int option;
							cin >> option;
							switch (option) {
								case 1: {
									selectSomething();
									break;
								}
								case 2: {
									{ ECPGdisconnect(__LINE__, "ALL");}
#line 697 "db.pgc"

									return 0;
								}
							}
						}
					}
					break;
				}
				
			case 2: {
					create_conn("postgres@localhost:5432", "postgres", "qwer");
					cout << "Enter your role(admin or user)" << endl;
					string role;
					cin >> role;
					const char *r = role.c_str();
					cout << "Enter name:" << endl;
					cin >> name;
					const char *n = name.c_str();
					cout << "Enter password:" << endl;
					cin >> password;
					const char *p = password.c_str();
					cout << "Role creation..." << endl;
					create_role(r, n, p);
					string a;
					cin >> a;
					break;
				}
		}
    
}