alter table SYS_ENTITY_SNAPSHOT add STRING_ENTITY_ID varchar(255)^
alter table SYS_ENTITY_SNAPSHOT add INT_ENTITY_ID integer^
alter table SYS_ENTITY_SNAPSHOT add LONG_ENTITY_ID bigint^

alter table SYS_ENTITY_SNAPSHOT alter column ENTITY_ID uniqueidentifier null^

create index IDX_SYS_ENTITY_SNAPSHOT_SENTITY_ID on SYS_ENTITY_SNAPSHOT (STRING_ENTITY_ID)^
create index IDX_SYS_ENTITY_SNAPSHOT_IENTITY_ID on SYS_ENTITY_SNAPSHOT (INT_ENTITY_ID)^
create index IDX_SYS_ENTITY_SNAPSHOT_LENTITY_ID on SYS_ENTITY_SNAPSHOT (LONG_ENTITY_ID)^