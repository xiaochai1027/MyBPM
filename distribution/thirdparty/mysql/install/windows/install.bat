@echo off

set DBNAME=i360r_bpm

if "%1" == "" goto skipParseInput
set DBNAME=%1
:skipParseInput

set DBUSER=root
set DBPASS=p@swrd123
set SQL_DIR=..\..\script
set ENCODING=utf8mb4
set DB_CONNECTION=--user=%DBUSER% --password=%DBPASS%
set SCHEMA_CONNECTION=--user=%DBUSER% --password=%DBPASS% --database=%DBNAME% --default-character-set=%ENCODING%

echo -------------------------------------------------------
echo Drop and create content schema ...

echo exit | mysql %DB_CONNECTION% --execute="DROP SCHEMA IF EXISTS %DBNAME%;"
echo exit | mysql %DB_CONNECTION% --execute="CREATE SCHEMA %DBNAME% DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;"

echo -------------------------------------------------------
echo Create activiti table ...

echo exit | mysql %SCHEMA_CONNECTION% < %SQL_DIR%\table\create_activiti.sql

echo -------------------------------------------------------
echo Create bpm table ...

echo exit | mysql %SCHEMA_CONNECTION% < %SQL_DIR%\table\create_table.sql

echo -------------------------------------------------------
echo Create quartz table ...

echo exit | mysql %SCHEMA_CONNECTION% < %SQL_DIR%\table\create_quartz.sql

echo -------------------------------------------------------
echo Create seed data ...

echo exit | mysql %SCHEMA_CONNECTION% < %SQL_DIR%\data\create_seed_data.sql
echo exit | mysql %SCHEMA_CONNECTION% < %SQL_DIR%\data\windows\create_seed_data.sql

echo -------------------------------------------------------


echo Install database complete ...
pause