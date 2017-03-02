#!/bin/sh

DBNAME=i360r_bpm

if [ "$1" != "" ];then
    DBNAME=$1
fi

DBUSER=root
DBPASS=p@swrd123
SQL_DIR=../../script
ENCODING=utf8mb4
DB_CONNECTION="--user=${DBUSER} --password=${DBPASS}"
SCHEMA_CONNECTION="--user=${DBUSER} --password=${DBPASS} --database=${DBNAME} --default-character-set=${ENCODING}"

echo "-------------------------------------------------------"
echo "Drop and create bpm schema ..."

mysql ${DB_CONNECTION} --execute="DROP SCHEMA IF EXISTS ${DBNAME};"
mysql ${DB_CONNECTION} --execute="CREATE SCHEMA ${DBNAME} DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;"

echo "-------------------------------------------------------"
echo "Create activiti table ..."

mysql ${SCHEMA_CONNECTION} < ${SQL_DIR}/table/create_activiti.sql

echo "-------------------------------------------------------"
echo "Create bpm table ..."

mysql ${SCHEMA_CONNECTION} < ${SQL_DIR}/table/create_table.sql

echo "-------------------------------------------------------"
echo "Create quartz table ..."

mysql ${SCHEMA_CONNECTION} < ${SQL_DIR}/table/create_quartz.sql

echo "-------------------------------------------------------"
echo "Create seed data ..."

mysql ${SCHEMA_CONNECTION} < ${SQL_DIR}/data/create_seed_data.sql
mysql ${SCHEMA_CONNECTION} < ${SQL_DIR}/data/linux/create_seed_data.sql

echo "-------------------------------------------------------"

echo -------------------------------------------------------
echo Install database complete ...

