SELECT
	'' AS table_catalog,
	'' AS table_schema,
	a.Table_name,
	a.column_name,
CASE
	a.data_type
	WHEN 'CHAR' THEN
	( CASE WHEN a.data_length IS NULL THEN 'char' ELSE 'char' || '(' || a.data_length || ')' END )
	WHEN 'VARCHAR2' THEN
	( CASE WHEN a.data_length IS NULL THEN 'varchar' ELSE 'varchar' || '(' || a.data_length || ')' END )
	WHEN 'NUMBER' THEN
	(
CASE

	WHEN a.DATA_PRECISION IS NULL THEN
	'numeric' ELSE 'numeric' || '(' || a.DATA_PRECISION || ',' || a.DATA_SCALE || ')'
END
	)
	WHEN 'INTEGER' THEN
	'integer'
	WHEN 'FLOAT' THEN
	'float'
	WHEN 'LONG' THEN
	'long'
	WHEN 'DATE' THEN
	'date'
	WHEN 'TIMESTAMP(6)' THEN
	'timestamp(6)'
	WHEN 'TIMESTAMP(6) WITH TIME ZONE' THEN
	'timestamp(6) with time zone'
	WHEN 'TIMESTAMP(6) WITH LOCAL TIME ZONE' THEN
	'timestamp(6) with local time zone' ELSE 'not supported'
	END AS data_type,
CASE
		a.nullable
		WHEN 'N' THEN
		'required' ELSE 'notRequired'
	END AS if_required
FROM
	all_tab_columns a
WHERE a.OWNER = '#{schema}'
AND a.Table_name = '#{tbName}'