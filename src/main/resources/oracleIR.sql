SELECT
	a.Table_name,
	a.column_name,
CASE
	a.data_type
	WHEN 'CHAR' THEN
	'char'
	WHEN 'NCHAR' THEN
	'char'
	WHEN 'VARCHAR2' THEN
	'varchar'
	WHEN 'NVARCHAR2' THEN
	'varchar'
	WHEN 'NUMBER' THEN
	'numeric'
	WHEN 'INTEGER' THEN
	'integer'
	WHEN 'FLOAT' THEN
	'float'
	WHEN 'LONG' THEN
	'long'
	WHEN 'DATE' THEN
	'date'
	WHEN 'TIMESTAMP(6)' THEN
	'timestamp'
	WHEN 'TIMESTAMP(6) WITH TIME ZONE' THEN
	'timestampz'
	WHEN 'TIMESTAMP(6) WITH LOCAL TIME ZONE' THEN
	'timestampz' ELSE 'not supported'
	END AS data_type,
	a.data_length,
	a.data_precision,
CASE
	a.nullable
	WHEN 'N' THEN
	'required' ELSE 'notRequired'
	END AS if_required,
	a.column_id,
	b.comments
FROM
	user_tab_columns a
	LEFT JOIN user_col_comments b ON a.TABLE_NAME = b.table_name
	AND a.COLUMN_NAME = b.column_name
	LEFT JOIN user_tables c ON a.table_name = c.table_name
WHERE
	c.tablespace_name = '#{schema}'
	AND a.Table_name = '#{tbName}'