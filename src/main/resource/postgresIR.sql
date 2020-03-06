select table_catalog, table_schema, table_name, column_name,
case data_type when 'bigint' then 'int(8)'
			   when 'integer' then 'int(4)'
               when 'smallint' then 'int(2)'
               when 'numeric' then
					(case when numeric_precision is null then 'numeric'
						 else 'numeric'||'('||numeric_precision||','||numeric_scale||')'
					end)
               when 'real' then 'float'
               when 'double precision' then 'double'
               when 'bigserial' then 'int(8)'
               when 'smallserial' then 'int(2)'
               when 'character' then 'char'||'('||coalesce(character_maximum_length,1)||')'
               when 'character varying' then
					(case when character_maximum_length is null then 'varchar'
						 else 'varchar'||'('||character_maximum_length||')'
					end)
               when 'timestamp without time zone' then 'timestamp'||'('||datetime_precision||')'
               when 'time without time zone' then 'time'||'('||datetime_precision||')'
               when 'timestamp with time zone' then 'timestamp'||'('||datetime_precision||')'|| ' with time zone'
               when 'time with time zone' then 'time'||'('||datetime_precision||')'|| ' with time zone'
               when 'date' then 'date'
			   when 'boolean' then 'boolean'
               else 'not supported'
end as data_type,
case is_nullable when 'NO' then 'required' else 'notRequired' end as if_required
from information_schema.columns
where table_catalog = '#{dbName}'
and table_schema = '#{schema}'
and table_name = '#{tbName}'