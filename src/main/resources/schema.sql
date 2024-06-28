CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS pg_trgm;

create table account
(
    id                uuid primary key      default uuid_generate_v4(),
    username          varchar(250) NOT NULL,
    password          varchar      NOT NULL,
    role varchar(10)  NOT NULL default 'USER',
    balance           decimal      NOT NULL default 0,
    constraint account_role_check check (role in ('ADMIN', 'USER'))
);
comment on table account is 'Аккаунт';

create table auction
(
    id         uuid primary key      default uuid_generate_v4(),
    name       varchar(100) NOT NULL,
    account_id uuid         NOT NULL,
    status     varchar(10)  NOT NULL default 'OPEN',
    foreign key (account_id) references account (id),
    constraint auction_status_check check (status in ('OPEN', 'CLOSE'))
);
comment on table auction is 'Аукцион';

create table lot
(
    id          uuid primary key       default uuid_generate_v4(),
    name        varchar(100)  NOT NULL,
    description varchar(1000) NOT NULL,
    status      varchar(10)   NOT NULL default 'OPEN',
    auction_id  uuid          NOT NULL,
    foreign key (auction_id) references auction (id),
    constraint lot_status_check check (status in ('OPEN', 'CLOSE'))
);
comment on table lot is 'Лот';

create table bid
(
    id          uuid primary key                     default uuid_generate_v4(),
    amount      decimal                     NOT NULL default 1000,
    create_date timestamp(6) with time zone NOT NULL default now(),
    update_date timestamp(6) with time zone NOT NULL default now(),
    lot_id      uuid                        NOT NULL,
    account_id  uuid                        NOT NULL,
    foreign key (lot_id) references lot (id),
    foreign key (account_id) references account (id)
);
comment on table bid is 'Ставка';

create table award
(
    id         serial primary key,
    lot_id     uuid NOT NULL,
    account_id uuid NOT NULL,
    foreign key (lot_id) references lot (id),
    foreign key (account_id) references account (id)
);
comment on table award is 'Выигранные лоты';

create table auction_role
(
    id         bigserial primary key,
    account_id uuid NOT NULL,
    auction_id uuid NOT NULL,
    type       varchar(50) NOT NULL
);

-- Searching with trigram gist index for finding similar strings for input string
create index "tbl_auction_name_trgm_idx" on "auction" USING gist ("name" gist_trgm_ops);
