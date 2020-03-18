SELECT
	'' AS table_catalog,
	'' AS table_schema,
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
	END AS if_required
FROM all_tab_columns a
WHERE a.OWNER = '#{schema}'
  AND a.Table_name = '#{tbName}'