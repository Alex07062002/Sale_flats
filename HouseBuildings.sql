PGDMP                         z            House_building    14.5    14.5 >    J
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            K
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            L
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            M
           1262    16466    House_building    DATABASE     m   CREATE DATABASE "House_building" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
     DROP DATABASE "House_building";
                postgres    false            L           1247    16794    person_role    TYPE     D   CREATE TYPE public.person_role AS ENUM (
    'admin',
    'user'
);
    DROP TYPE public.person_role;
       public          postgres    false            F           1247    16750    status_of_contract    TYPE     �   CREATE TYPE public.status_of_contract AS ENUM (
    'заключён',
    'в рассмотрении',
    'создан',
    'аннулирован'
);
 %   DROP TYPE public.status_of_contract;
       public          postgres    false            I           1247    16760    type_of_pay    TYPE     r   CREATE TYPE public.type_of_pay AS ENUM (
    'наличными',
    'безналичный рассчёт'
);
    DROP TYPE public.type_of_pay;
       public          postgres    false            C           1247    16743    type_owner_of_flat    TYPE     �   CREATE TYPE public.type_owner_of_flat AS ENUM (
    'свободна',
    'зарезервирована',
    'продана'
);
 %   DROP TYPE public.type_owner_of_flat;
       public          postgres    false            d           1247    25029 
   type_price    TYPE     M   CREATE TYPE public.type_price AS ENUM (
    'full',
    'low',
    'sale'
);
    DROP TYPE public.type_price;
       public          postgres    false            �            1259    16467    city    TABLE     e   CREATE TABLE public.city (
    city_id integer NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.city;
       public         heap    postgres    false            �            1259    16734    City_CITY_ID_seq    SEQUENCE     �   ALTER TABLE public.city ALTER COLUMN city_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."City_CITY_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    209            �            1259    16714    contract    TABLE     �   CREATE TABLE public.contract (
    contract_id integer NOT NULL,
    flat_id integer NOT NULL,
    person_id integer NOT NULL,
    type_of_pay public.type_of_pay,
    status_of_contract public.status_of_contract
);
    DROP TABLE public.contract;
       public         heap    postgres    false    838    841            �            1259    16792    Contract_CONTRACT_ID_seq    SEQUENCE     �   ALTER TABLE public.contract ALTER COLUMN contract_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Contract_CONTRACT_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    213            �            1259    16520    flat    TABLE     =  CREATE TABLE public.flat (
    flat_id integer NOT NULL,
    floor_id integer NOT NULL,
    number_of_rooms integer NOT NULL,
    entrance integer NOT NULL,
    cost numeric NOT NULL,
    square numeric NOT NULL,
    type_owner_of_flat public.type_owner_of_flat NOT NULL,
    type_price public.type_price NOT NULL
);
    DROP TABLE public.flat;
       public         heap    postgres    false    868    835            �            1259    16731    Flat_FLAT_ID_seq    SEQUENCE     �   ALTER TABLE public.flat ALTER COLUMN flat_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Flat_FLAT_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    212            �            1259    16474    house    TABLE     !  CREATE TABLE public.house (
    house_id integer NOT NULL,
    city_id integer NOT NULL,
    address character varying(200) NOT NULL,
    name character varying(150) NOT NULL,
    constructionstartdate date NOT NULL,
    constructioncompletiondate date NOT NULL,
    commissioning date
);
    DROP TABLE public.house;
       public         heap    postgres    false            �            1259    16733    House_HOUSE_ID_seq    SEQUENCE     �   ALTER TABLE public.house ALTER COLUMN house_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."House_HOUSE_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    210            �            1259    25023    cost_for_m2    TABLE     d   CREATE TABLE public.cost_for_m2 (
    count_of_rooms integer NOT NULL,
    cost integer NOT NULL
);
    DROP TABLE public.cost_for_m2;
       public         heap    postgres    false            �            1259    16508    floor    TABLE     x   CREATE TABLE public.floor (
    floor_id integer NOT NULL,
    house_id integer NOT NULL,
    floor integer NOT NULL
);
    DROP TABLE public.floor;
       public         heap    postgres    false            �            1259    16838    floor_floor_id_seq    SEQUENCE     �   ALTER TABLE public.floor ALTER COLUMN floor_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.floor_floor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    211            �            1259    25035    multiplier_for_cost    TABLE     o   CREATE TABLE public.multiplier_for_cost (
    type public.type_price NOT NULL,
    multiplier real NOT NULL
);
 '   DROP TABLE public.multiplier_for_cost;
       public         heap    postgres    false    868            �            1259    16777    person    TABLE       CREATE TABLE public.person (
    person_id integer NOT NULL,
    name_surname character varying(100) NOT NULL,
    email character varying(50) NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    role public.person_role NOT NULL
);
    DROP TABLE public.person;
       public         heap    postgres    false    844            �            1259    16819    person_PERSON_ID_seq    SEQUENCE     �   ALTER TABLE public.person ALTER COLUMN person_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."person_PERSON_ID_seq"
    START WITH 1001
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            :
          0    16467    city 
   TABLE DATA           -   COPY public.city (city_id, name) FROM stdin;
    public          postgres    false    209   �G       >
          0    16714    contract 
   TABLE DATA           d   COPY public.contract (contract_id, flat_id, person_id, type_of_pay, status_of_contract) FROM stdin;
    public          postgres    false    213   �H       F
          0    25023    cost_for_m2 
   TABLE DATA           ;   COPY public.cost_for_m2 (count_of_rooms, cost) FROM stdin;
    public          postgres    false    221   'I       =
          0    16520    flat 
   TABLE DATA           z   COPY public.flat (flat_id, floor_id, number_of_rooms, entrance, cost, square, type_owner_of_flat, type_price) FROM stdin;
    public          postgres    false    212   YI       <
          0    16508    floor 
   TABLE DATA           :   COPY public.floor (floor_id, house_id, floor) FROM stdin;
    public          postgres    false    211   �I       ;
          0    16474    house 
   TABLE DATA           �   COPY public.house (house_id, city_id, address, name, constructionstartdate, constructioncompletiondate, commissioning) FROM stdin;
    public          postgres    false    210   @J       G
          0    25035    multiplier_for_cost 
   TABLE DATA           ?   COPY public.multiplier_for_cost (type, multiplier) FROM stdin;
    public          postgres    false    222   wK       B
          0    16777    person 
   TABLE DATA           W   COPY public.person (person_id, name_surname, email, login, password, role) FROM stdin;
    public          postgres    false    217   �K       N
           0    0    City_CITY_ID_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."City_CITY_ID_seq"', 12, true);
          public          postgres    false    216            O
           0    0    Contract_CONTRACT_ID_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."Contract_CONTRACT_ID_seq"', 5, true);
          public          postgres    false    218            P
           0    0    Flat_FLAT_ID_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public."Flat_FLAT_ID_seq"', 21, true);
          public          postgres    false    214            Q
           0    0    House_HOUSE_ID_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public."House_HOUSE_ID_seq"', 7, true);
          public          postgres    false    215            R
           0    0    floor_floor_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.floor_floor_id_seq', 13, true);
          public          postgres    false    220            S
           0    0    person_PERSON_ID_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public."person_PERSON_ID_seq"', 1011, true);
          public          postgres    false    219            �           2606    16693    city City_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.city
    ADD CONSTRAINT "City_pkey" PRIMARY KEY (city_id);
 :   ALTER TABLE ONLY public.city DROP CONSTRAINT "City_pkey";
       public            postgres    false    209            �           2606    16718    contract Contract_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.contract
    ADD CONSTRAINT "Contract_pkey" PRIMARY KEY (contract_id);
 B   ALTER TABLE ONLY public.contract DROP CONSTRAINT "Contract_pkey";
       public            postgres    false    213            �           2606    16604    flat Flat_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.flat
    ADD CONSTRAINT "Flat_pkey" PRIMARY KEY (flat_id);
 :   ALTER TABLE ONLY public.flat DROP CONSTRAINT "Flat_pkey";
       public            postgres    false    212            �           2606    16668    house House_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.house
    ADD CONSTRAINT "House_pkey" PRIMARY KEY (house_id);
 <   ALTER TABLE ONLY public.house DROP CONSTRAINT "House_pkey";
       public            postgres    false    210            �           2606    16737    flat check_cost    CHECK CONSTRAINT     _   ALTER TABLE public.flat
    ADD CONSTRAINT check_cost CHECK ((cost > (0)::numeric)) NOT VALID;
 4   ALTER TABLE public.flat DROP CONSTRAINT check_cost;
       public          postgres    false    212    212            �           2606    16735    house check_date    CHECK CONSTRAINT        ALTER TABLE public.house
    ADD CONSTRAINT check_date CHECK ((constructionstartdate < constructioncompletiondate)) NOT VALID;
 5   ALTER TABLE public.house DROP CONSTRAINT check_date;
       public          postgres    false    210    210    210    210            �           2606    16736    house check_date_commissioning    CHECK CONSTRAINT     �   ALTER TABLE public.house
    ADD CONSTRAINT check_date_commissioning CHECK ((constructionstartdate < commissioning)) NOT VALID;
 C   ALTER TABLE public.house DROP CONSTRAINT check_date_commissioning;
       public          postgres    false    210    210    210    210            �           2606    16739    flat check_entrance    CHECK CONSTRAINT     \   ALTER TABLE public.flat
    ADD CONSTRAINT check_entrance CHECK ((entrance > 0)) NOT VALID;
 8   ALTER TABLE public.flat DROP CONSTRAINT check_entrance;
       public          postgres    false    212    212            �           2606    25040    multiplier_for_cost check_real    CHECK CONSTRAINT     �   ALTER TABLE public.multiplier_for_cost
    ADD CONSTRAINT check_real CHECK (((multiplier <= (1)::double precision) AND (multiplier > (0)::double precision))) NOT VALID;
 C   ALTER TABLE public.multiplier_for_cost DROP CONSTRAINT check_real;
       public          postgres    false    222    222            �           2606    16740    flat check_rooms    CHECK CONSTRAINT     `   ALTER TABLE public.flat
    ADD CONSTRAINT check_rooms CHECK ((number_of_rooms > 0)) NOT VALID;
 5   ALTER TABLE public.flat DROP CONSTRAINT check_rooms;
       public          postgres    false    212    212            �           2606    25027    cost_for_m2 cost_for_m2_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.cost_for_m2
    ADD CONSTRAINT cost_for_m2_pkey PRIMARY KEY (count_of_rooms);
 F   ALTER TABLE ONLY public.cost_for_m2 DROP CONSTRAINT cost_for_m2_pkey;
       public            postgres    false    221            �           2606    16837    floor floor_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.floor
    ADD CONSTRAINT floor_pkey PRIMARY KEY (floor_id);
 :   ALTER TABLE ONLY public.floor DROP CONSTRAINT floor_pkey;
       public            postgres    false    211            �           2606    25039 ,   multiplier_for_cost multiplier_for_cost_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.multiplier_for_cost
    ADD CONSTRAINT multiplier_for_cost_pkey PRIMARY KEY (type);
 V   ALTER TABLE ONLY public.multiplier_for_cost DROP CONSTRAINT multiplier_for_cost_pkey;
       public            postgres    false    222            �           2606    16832    city name_unique 
   CONSTRAINT     K   ALTER TABLE ONLY public.city
    ADD CONSTRAINT name_unique UNIQUE (name);
 :   ALTER TABLE ONLY public.city DROP CONSTRAINT name_unique;
       public            postgres    false    209            �           2606    16781    person person_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (person_id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    217            �           2606    16783    person unique_login 
   CONSTRAINT     O   ALTER TABLE ONLY public.person
    ADD CONSTRAINT unique_login UNIQUE (login);
 =   ALTER TABLE ONLY public.person DROP CONSTRAINT unique_login;
       public            postgres    false    217            �           1259    16676    fki_foreign_key    INDEX     D   CREATE INDEX fki_foreign_key ON public.house USING btree (city_id);
 #   DROP INDEX public.fki_foreign_key;
       public            postgres    false    210            �           1259    16724    fki_foreign_key_flat    INDEX     L   CREATE INDEX fki_foreign_key_flat ON public.contract USING btree (flat_id);
 (   DROP INDEX public.fki_foreign_key_flat;
       public            postgres    false    213            �           1259    16730    fki_foreign_key_person    INDEX     P   CREATE INDEX fki_foreign_key_person ON public.contract USING btree (person_id);
 *   DROP INDEX public.fki_foreign_key_person;
       public            postgres    false    213            �           1259    16789    fki_p    INDEX     ?   CREATE INDEX fki_p ON public.contract USING btree (person_id);
    DROP INDEX public.fki_p;
       public            postgres    false    213            �           2606    16704    house foreign_key 
   FK CONSTRAINT     ~   ALTER TABLE ONLY public.house
    ADD CONSTRAINT foreign_key FOREIGN KEY (city_id) REFERENCES public.city(city_id) NOT VALID;
 ;   ALTER TABLE ONLY public.house DROP CONSTRAINT foreign_key;
       public          postgres    false    3219    210    209            �           2606    16839    floor foreign_key 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.floor
    ADD CONSTRAINT foreign_key FOREIGN KEY (house_id) REFERENCES public.house(house_id) NOT VALID;
 ;   ALTER TABLE ONLY public.floor DROP CONSTRAINT foreign_key;
       public          postgres    false    3223    210    211            �           2606    16844    flat foreign_key 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.flat
    ADD CONSTRAINT foreign_key FOREIGN KEY (floor_id) REFERENCES public.floor(floor_id) NOT VALID;
 :   ALTER TABLE ONLY public.flat DROP CONSTRAINT foreign_key;
       public          postgres    false    3226    212    211            �           2606    16719    contract foreign_key_flat 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.contract
    ADD CONSTRAINT foreign_key_flat FOREIGN KEY (flat_id) REFERENCES public.flat(flat_id) NOT VALID;
 C   ALTER TABLE ONLY public.contract DROP CONSTRAINT foreign_key_flat;
       public          postgres    false    3228    213    212            �           2606    16823    contract foreign_key_person 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.contract
    ADD CONSTRAINT foreign_key_person FOREIGN KEY (person_id) REFERENCES public.person(person_id) NOT VALID;
 E   ALTER TABLE ONLY public.contract DROP CONSTRAINT foreign_key_person;
       public          postgres    false    3235    217    213            :
   �   x�5��
�@D�vT�z������ā��'d��a��+8����<;�-��2t:\X�p:�-�G�'^:�X8�7[M�16�;
T�!��\P���'y�Bp#�8��g�M̒8]
����E�+�����������-x�У3\Q��롪~��w�      >
   �   x���K�0DדSp��$�w�0-,�@B�&Q"��3�o��u$�c?[o����̜Yt�w.,Ё?|�bu�!�P
��rև��4���b���A{f���M�����m��YX����̦�+Ф�o���9��8�      F
   "   x�3�45 .cNS�ˈ��7�4�1z\\\ n.�      =
   �   x���4�@0�46�3��xaӅ}6�{/l���/�24*3a�R3=sJ͈WjTHXiZiN��P�1P�1T��)б�_l �ۀ�����_� T�Hoқ.� �܄��� �-@��ad�M%v���qqq �O�.      <
   <   x�
��
�@��]���z��:��8�D[q��<8�R�1>ʍ���R���.�?�	�      ;
   '  x����J�0���S��Sn��ؗ�	�a�
��Q� ".�2?贶�
'o�MǍ�\����s-�p��O��0�u����_G��QT(m}�+M�bv*���ѠÈUX߱K�w���;� Mw�OR�b�۽�ĊםkR��J�V&���/1��ҵ�"�q�M����[��,9���<�h�����j�Vx��8�'�gM�}��T�R���Nb��	���h���Ej������\�(���Z��;���~�F�[��όl�~�E~��QBC�����0ˤ����'�      G
   %   x�+N�I�4г0���/2,M��Jsr8
�b���� �T      B
      x���ٲ�ȑ-���
}�6������y&�~�� 	H �'���j��Ri�*Y�S��~��rP�R9�/��G�=Aȝ�~I�t"�ܗ�/_!	���w�����������?��ˏ�}��ǯ?~'t��	�r�����W-K�໳�k?͂�p�9��Y�x��ͩp����������ç�~�����x��}��3�$o��9�a�*0��^ī�U9D�o?E�µ�B�y��ϊꌻ�k������ø0|�/pȟ|�W���?~��`��a�����[I��o�؞��&8>m�&�O��y�C:�*|�7�Oat�
8���c�s� r�/'oM��P�M����Q��;����\�)$���|=ԝ�4��}:�&|�k����_������a�`p�@ȹs�k{��	5KQ?ر�o��7�Љm�xa1�:��H������է?"_N��W02.��0��A���5;�C�>l`�`�4u/���]/8�O�(�`��lV拭�q{��p:>��ӟӑ���������'om{�9�j⓻sb��ҷc]������ˀf�`������x���Mv�b�sXV�5�h��0��Cc;�ai+���I���T�����7l�~ˉ�UX�󷾻��ؖ(~�VN�<�R���,��t�������ۙ��7�tXI�|�.��������&#/��ʡ�o"]S��y�(��p�Wv7���d>x9��$rS���s���0����-Jk��S8	��(��h5�5>'��9Q��%�ǟ�S�~�?������e{o�����<RTC���I�>��l%T6AH��*��B�ii���F>��J-�n�������w�ٽ��/�x��|?�vGvb�s<ϲT����ً�Z�xs[��WQn�ۭ�����iNҙ�V�%��Wlm�����x<�};��
�v7��%��Kg;�cr�������v���A{�:4Wͦ�����
���Or'�����
퓃�����n�M��n�%�om�{;u�^��WnΣ�{�N�"]��O�g�� ؾ��S6������
�ȳ��%דe{'��X���O2�w��q�aS�nYg9߾�*<��<XWmU7/�=�$7��g�r�gV�3�ث�fp��,I�`�|z��$yhNm?|ᢼ��~\��W�y�<٧�[��|�Vrc�Ɇ�s�z�����ίrÞۺ,\��h�t��
�p3�1C�*}�㏿�����	Eg�uy����d���m�L�h`�+����	N��զ�����%n�����Fj������ȯ��Z�;����;['������k>�A{%(�f�r��'��2]j\d�m���o��x�ﯮ���
`dJ����Փ�Ш�͑�L�����ߓ�~���+
��;X��{؉��"�=�p�Ƞ�i���_��Ͽ�T�yR1���p
���^2�w�Ah���s�ƶ0�Tt%s�������CW()��A>ך��a����
������.t��c`{����B�������j�����p�����/:i�g�`:r:��`�+^�g��m�
�˺��9�Ι�o�zX�'86�0I&�d���tP��� �`�ӟ1 ����|O�^3����n�HR4�C��\�#Km����t9���GH��~_���\����s5��ē�[��E�%_ e��� �1��|g@V]��V}��	55�l�|�
�n��}����d��(Tc��3R��`�z#�lG�b��6�5Y���2�!tD�$�_��i,�dT_�)h�����8�7������9��'p�o�C6D�U8�p
j�m7o��#F�� ����i���t'���_��	��B����6�}G�$9sn�|�)�O?����e6��`RO�O���%����8	|2X�pi����G'�aUlQ&�e�9���u���⮓ZE�,,���N���OM�����;v�;�,��8�����K�0~>��]����M�\�\ap����
��Gfy�� z d"����Ũ�|Y�����.UC��5����m$�2\�/�on6�[1�}�k�,J�?�4�L0��.ݥ;,]E}�Xa�>^��O٢�f���-0L x뭾�}M3�����*E.1�|��_xNzƺ����qmXJ�7�]����N�q�
�8��1�S7Ru85� LO�@>ŋ.`'w-����Ue�ܼ�))"%>��&ں��ES�p�B�st�M-�[D�OG;rqК��:z9��*V�>⊠����Q�UN�/�pD7t��! sw��ۇ-�H��+(�<���%=ߪ��QZ>��s?�Ru+c^���z�갵�l9̏���jg�#>�M|�2z��om_U��O���Z���O�f�����] A���u��	n�S��ĞQ��b5���p(�8�ʥC�-�����_t��
��o�U�$�|o#�U{��Nzi���pW����*E��߮ �t�� ��c�ITE��>,��di�#@8�jl�;�x���9��Y:���%7{�/��ó�g��)���a�n����0��9BD���L��n��J[����<�.��ѐ�}�T
�b����
I�0�إ'��$�r�
���m���۰b^�ip�W<N���80��� �j��Y��& 1����`�
b6�y��h�~��8��:��S��p:"B��J��W*��N=h)6Q���A��0��[�L(jH0Ro�|,Ŀ<.��<�JR�&J�~i�{�>���D�H-�_PK�ʯ0�����C9se��,��[
	��Ù��6�`Q��}.Ws��<'^B�M���yG�%�g��`�����*V�!�Q�p.�Vj��JǄ��<�lE~E��� U�n��/%]�>��wm���Y h:\׍�*�S��)p#�/�,	M�9p���*x�55��e�m뺥�d�LR�a��OI��Z���Wo�|)��i�V��"�P����~mi�4��5<
_�<�W���;Dp��
�w�%D�.��޸[��3@���?jT��7=���I'�q�A��$��[rjA2s e�+�D���}��8Y+�D��~
L������EWD� �
��o�3�����9ڞ��Տ!�	��H���X|)���h&.������8��=w�v�
F�eI��!M;>L:����´��Wq���{�xR�-�z[h�~`+�:i��؞�b�`q�yX&�vr���R:�jS</�Ѳ�^�p0�<��]��=����*�����
�aw�����IH	�N��U��j#v��
����,F'�S;^�:���#�qG�
���_���G�>���X�JR:��n��Ǩ�:
]8��m;�@����|ǡ����o�h�*�F�f�&u�MT(@��O��]
z?�	�G��^�R5Y����{�Vڭ��z!ޤ���̎���4����������Ӝ9�!:l�D-��Vu�ǋ��Z��]"~e�Ez��!z� �eѲ��޴�6�+�W(>��r����G.��4�������M,?8ַ<�誡Ȝ=�'G��T�䄳�����E7���@�[��љV��������(Yܐu��"=���D�Z�
��J���Y=uz䘱���E�SL첮�r���$ߓ�n�|g��j�Ws^��l�1���2���!Z��
!�5��0��/C4!�*L<�%Z@�!l���P�9�4ՌuTN��]����ȥυ�BY��/��%�����Ϛ�&}��Ka�!e��ͽ!"�Q���
&��[>�HA�n(��=�W旒���̝i4�_�y�|iݎ"�G |��%����À��ę-���m��l:�u�ߨO�
�y��SxKC4h��)���L��˲�� ��L����ڲn�Q�g���
}�S�,c��pd���^J<(O4�B��bZkX�([C�G�\�43����}��͊0��:`1�z,���ē�/
�����/�{�-�N�Lj���W��b�I���`\na���x�d�MhJ`��j'tJr�()��^O� �ߪ<���    �r�9�kK�2��+�G��f�+U��N;/�`�:��X;|n��,A4�
�˷TW�.CT����B�<i�k�����P����U1���Z^�8԰H��f�F�p�>��\�m����D�-5&��:[jc����vf�D���b�N����!������l7�hs��焆��os�	fq���wP≙۬[��0��N����K�[r4��p�+_3l߲۹���i���b��/��d�mR�i��XIvxJpq���J�f���eΝ5��Ҧ(�j�=J�N:��Pw�k�H�Q7D}�>��ħ^Q��۳ЍiL
N��"i�K(�r����!������R�,қ���Bw��S2�!�d��ח���iM˅Ƴ�z9S�ɧ��5���!!08��ƺ)�\|����/���z9m��I�] �/����eG��e
�쾍�o,���2��?���-Lds�Dz�����9�b׋��tY�?Ő/!�m)�4����nR��O�>�$f�l��X9�x�O)��$'��6�Cw���'w��כ�&��hR�KEk��t
+���y�ݢ�!�
`��̐��N�>�NHZ�C���F��%|�[��fAK΀M�X����ӹ�K�~�����2�B5ر�cJw�V��񘐪����ۗ@kڃIC�$V��Yb�3�e3r��,�����+��/�F�\88`37+�~���/���ʥނ���p�?����U�;��"�������LE�c
g�mi�.�t�m��HKkB��ŇL
R�z�H*,jn<�a"���P��t1KM��Q��-�V���l,�(
�����,]���Q�����g���1��%���}<v}���k��PE6M1������x�u��8?l��Ђ�W��P�A�RK�Ҵ0ߐT�/�_��e�r��xb�Ɲ�6N݂e��!,���狏_���(��[>89n�|Q��p>bw{io�:�e�[����ú�f,뮐�G�w�d{��	C|YS2^��bQ� F�E����b���wm���^sJ遡d��ED�
 E����6_7�[�\�]��7��N����V�����=�����$~"w���S��]�tK��څM"_������қ\e��d����w�J�;8�K��f�!���cT?O��R����&SJYD�6
ap��<�A����[��&r��~�7X��1MD
��_M'_�+�=�1�7Ô!:�f�����$I�Plc2+��Qmt�.G��<�y�9>�S͒yϷ	�� ú�0ؖZ������l�ll�GB���]>t�����-��ɔ:q���.�3%7��ǎ1����Dh���-f�8������:��t֪��v���a�݅|�2���B��fl3��x���՟c�sC<�i_�N�8�l�3�Z)���X�6��0,|7��/R��ۛѫ�/gr��D�fb1�#����oW�a}(�N�G����-��BR�&�� m���āi\�B.���`2����,�G��&�$Ϧ��[�y�q���_=Or�?�!g.y�����s�ɸ���s�y�"�$o��W,�]�=Y
DϞ�l�x{��DW�L.�n��4/D�\���
q�/捉�%^��<�����g�/.�-����u���bu��F��Fl�l�%k,:�����WZW4�z� �da?
�ü9Wِ�����:Nʊ� �H�$��I�%�/�ګ��h��撍������\��r�nm0��3=��E�ȭD[vIj�H5K��{qߏ/�zg�a�H��)�K���q�@��)B�Q	-�s�P�q`�1�������?d�n������|&��
Nz@fdZ�£Lrk׺,L*�Yܻ��L&V�b�&d
� �o���m>�ְ�9D>�
��9k��~r0�C�ê_
b�����~��9e���`��nLJ�j��scR#�0�5#ߴ6�#�b�cS�G�{�������^����P��\���#��J6� X�z�;d�����pn�x�;GP�m�Pu���;��R�r8�7g#��e���5w� _a�O���Yc���o���P�v|�t��z]!�%����."����k�bᄉ,�F&i�@����nv�j����3m����Y�?;��&��Ub���0���kJz�._�+ʅOz�S^���5�^=�q@bU��'�B|0�2��v8KTn2����]�y�H#E[Y�I�ظH��!'��_���)s�o��EK����j�}}���1c8��;��/?��ӟq[�L������9��H��j�����(P��֪�l����3e�Ҫ��jg|��|�zX��mr,�pdW�Y���R�^�҈~M��_�����M}����΃��t�$*�A9�%i$;Ӆ����z�P(���D�`,7�?��xb8m�'D��X��hۉ���YԖ��L��Z��U~�<�4g��G׵L���O�^ҸQ����s8Z�ϣ�$�^���x������w�v#^���P���ʥ6�	�QٸT�nyd��\��G�T���-���ȝA�	��B� ��1���ѝ?ݘ�����s�+M�]-w����C�ѯS�y-�*Q4����l~����,	�Ox�|!9զ�~X/���s�b��F?�v�	%2ň���4�'�T b`�'B�����l:;)v&�]e���Y7Īw
�^�S�m` v)H��W�W(�p�R�g���(�����瞃udQ�HN�BP{����.�3�5�d�+6��Ô��RZDi�u0�d(|�ɥ �	����t7Sv����'��cǕ�� �|�:!�!e����pR����e۲��k�z	�c���%C}��BR��s�U��4�6E�o��Ҧ��v��f��޴�ƕ1��Sr�[��_�]�.��
��t$�8��w��`��IS�e��l��6ÈHJ����
�@c�]dC�G'15]��ث HS��2��$�b�ho�g�
II�� �&ߐ���z���5���"�7�K	e��:g_v^���/U���G��O$H�
���{C7��q/����D�T�'�DD)��u8�w4+���0�;ʑ58���V�W�F|��%~/��y��%K��W��Sh/ ���R}���lp�!���W���`O窨(\7��Ŝ�������v*�G���J
�uoe3���4���+y����X�g�.�a`����CDh�6�u�״�R�EU��ne������UtM�Q�X1�PعIN'î�	���ZZ����YZ�m���N0|���-a�����-��r`�̗ O)mN�V�E���ͦ<��W!Z�KtQ��q�'�ށ�)ӌ��;ϓ��	�O��\'�9�h��v/mZf�SXf��F3Z'^w7Q�����
��Zʓ ����O�';-|3Z, �&���s�TYU��iQ�DzƄ�6ӥ�nk/<Kyj����TjpKp�WԝFjM27m�@bq�"	��$���N�wma�s�q��+��� ����,1���~B-���a�p[��(��^WU�\�j��:���:��~`�_��-9�߳���$[�`C3�������'ݵ0��(���ƃ+z4;iv����q��0��c��QP��j��i�}NK�=�#rl�FN5����sMRf������Π6ϟ��~�X
���.��,<?�1v"y���Ǥ�FV�L��O��5����|�0�(H)����)��J�F�v�V�G��
S��|b�i�yc�����aӥ��'�ǎ?O�r�,�"j2�ظ�B�<���6A1/'�]۬�1�G��@R�2��_2�v�|m���oX�3k�V�!���k������&yl�kc��,���2s��oۛ��(����*p��T�y�ٕ�Z�uXO��D2:��z3�7 ��& K7xF	4O�}��0�x�r<3C���ѿ Fk�#^FAb7G�q��h
ns�	:����E�IQ�?B4��ð���4U�Y�т×����6�js�0n����L!����p�VZ���c�ɏ�
�-MX�V�au��    H��[?�q -� �c� �9ĺ\8�&�$;_\��1p򬆀�Lޗ��I0p定 ظ ��&��|I�7�E<n�n"!��J��]��-5$���8~{��m���y�C��?�,���m�9��>�H���<X;���O��a<��mu��{��%�_��8� 0O{*�^�&�qL
�(�^l[���&)a_����K��<7�R�9:� K*Sw|��	�=�5�=�e�:��Y��Dv(����ϳ�{�T�{��X�j]�|U���ѹ�%r/�\T��F�B�S�bsV�d�X{8�)u��$�[-�bD!+��Ʃ�����L�/����b�)TA�� ��S��#T��7��i�M���3}�x�X�%���a3����v��)u��Vر;��Ї��;�)Iٞ>��dCX-���xŒX��N�C��iV� P\dJ#	m�"
�auhʡ6ZY�Ŋ�%$Me:����_Bg��*A��*����-E%�7ɤX�
���u7��E8&�Ĕ�'�H6��Z�9�H��|�[Of��}mU1���u| �+�2G�x����m0�����ʝ���~%O��֥�H���?^b��������D�4>�L��hg{�h�ᡜ��F��4��L�F�ŭS
�
4F誦i��,8�m@�Jk���Y9�$�-p�8�_|�9�5��2'\�G+X}��\�v�8�4X��0�]�.��K���"�?��	��Мh-��Dp���Ei��2�쥽@�&��I^Z���Se���C�|��
�ȁ5��l%B̏��\���J���1
�@����GoD�^0��ͨ@��!8��gD�M 6����B+׭��N�z8x�~�O����$�Hm�u�	e�T<LB�[��I�YY��jmLVG�<�{��EG���oo��4��a%���zč��	�`����i.����o)ݑ�e��Re{~�8}D 9�-�ѷ����-C������#f!��.5G]�ש���حaB1,�K�̃i�`��ql��
���u�8�V<�aF�?��ߟ��O�a��6��:R����ٱM8�<I���(�����H��A;f�hL}ӷ����p}�6�����pm�M���06XK'|�ka�2����WBX�G6KO�
ފ�`k��s&������"[XS����Fvn�e�hȇ��P~ғ���@���a�tL������hs���`S����(�������ݪ�V}"����]�cJ���y��!�CVǓ����;����YWT@�'zr��N$oq�w��GT[��s{�5Bp�T#Wݺ�K�e�J�|�Z IIb/���v
.4We��a�<�bm�/�Ql�}6�E����L#q�O!�.�f��L�n���/�F�� �*s2��
����	�[� W��R.���ɺW��a��߰��?\$ m����!�5 �I����ewX�7�S�J(UF���lJ�t�f�[)����	�_$l���#x�Vu���k�V�Va��fx6��#��ꆋ-�6upF����Q���{y��<��fM�6u?o��q��,0\ꄲ�S^�-tJ�����y��P<�T������Of�@"��Ȩ;s
�h��	.\L�n�kwV��fjx�il���/S��h�q�}a��?����0��w:C�Ш�lt��>�~���:*[�m=K�)ӠhB�<Y.:Gu�����������n�4���7?�D*����.��I�oE�ț��g���s}���lA�)h��)|I"+ �ݹ��J�م���+�M��Fu����uo
/�I�A�;β�f�H9��)�l�_l��j�y�V�I8}���
�`���4[�6���T-��A��h���U7��yZMKl.��d}�M'%��ȡ�6��=��Z�L���lJ寧�~x�e�s �����R��LW���^F#)m�l�GV䭈_�\�}%����S�n,t�����r6���e6:�6�#sM	�ڌ;L�����R{�v��݋M���2n��Y$���z	��bho�0�>�"	p#��=�φ�f�2?#-�Y���tn��N��*����p�ǹ� Z
�ӯ���Ӫ�0��;��ك�����#"�����IL��U{]��i�F�X�6��K�	X-�8�D���a�-Y�T|%D�.U_�:y-�T�E%���7�NJOLP\�ŝu�XN�k�B'׊ڎOkl�K@�i��$���a�[M�ù��2���0ʩ�M�~���䤐Qu��
��3�=
��ʐ�lw;���
��������.�K4^0�&�>w������/(<� |4����*-�>Q���Vɯ���T�-vi�X���
�x_R���V 4f+Ӹ�*��/w�Aw�n�Jv�aCk4�x'f!�孅:�&Y�,Nk���Ô5��ɢm��ִ��׻]_,�)S�nI��2�[�<�	!O&=@���Ñ4�쬥�:گ�Wq�f��$FJcU��LSR�������n
Z�\SG
麯�]���!����
;�[�ƴ���.���d��u�L����D��F��|�yZ���8��-H7ls��|J��`��M�_<��W�w���}"=T�\.!0���~Z�����Li-�`l���F�	T7� ����3)�{���U�O��*�ON�n�(����l�lh9�@�N*e�Q_96�?ȆƷ��� �Z!0Z%Q�LN3��ؠ�1�/ni�l��Jښ���QEC���q�����^4��zߜ�E��.Y��lw�E�R�
�~'��:�}���C����"��9�ph��Ϧ���/ny����v �Y�DS�t��iN�n�����$��^��:��#ӥ �_9$%�"e��#��Tf�8��R�J���%��f2�?7ݎ����E��Ē4Y?�&��F
4<��D�4v���aldD�,�;2y�_�n@HB�0y�q�`!��h.�\����9���!Y�^������2;��#R$�4�tQh�+�ɲ�5��R�*�z�5YQ�$�\:�
͜hA���2���`�C�<�I����d7��Sz�f4Ä�=Knl1�ʔj���;��fa�N�|4�[^.X�,�if��;�����Y��0rKG_�'�D�)�SE۠���ԟ{ I�?����ꢱs�X�.����a��wBS�$�Fp@�Jڜ"�x����0�~���vj ���J�'/f�9>$K�ZS7��'�Iw,�$p�ahlo�R	v�f{X�b��{����q�l,�!+"D�\xo\ҁ$Ke*w_
evcլ �=���<#� B�Y$�w� '��	�uA)^
�}^b��H�)p���[��a��.��#7�M�ʰ��l���K}�u���Z�8IS��
y��q�����>�m�-���7�F{=��*���չ۩/�[�h�;��o(�J��z��gʅT��	kϡP�l#et��U'���SR3��$J�I����R	e�&�=܂L����Y��E_mW9�f�yS����f���BߌO�.{H�T!��e"R�O��V
���)�G}Xӎ}��T"��LSF�s�T�����ߌ���H�2o���df��y�8�uQ�w�d�Z��A�08İ4�Ix�2�H��T�n�+��37C�4���^��~�TCP���g�}	n�YnQ��iX�1��t�+�f)Biq��ضm���h�4O�����ۼ�ZM5�aY�G��l��`����N{_�p��nf�WKϙ>�M-��;�|=0�C&=�P�\-i��EC�ī�W~��su_ju�u�|-�b�^{�6��!�[�Ani:�ʅc�W�wmX�����ɺ�N+�M[�


��!\{���
B��)�u{mM/���"*���HF+ ��Թ7Mt %�[���t"���%I�LM_������GT����#\���:V��^�����V�n�d�`���b�<*�0�w¡�LӮ�]��m�9�Sf�)��n���Q�Rt�i$I��	�Z$ߟ*����:
�ԱN�o�J�$YOjK�
�l�w��X�
^~���Os{F�	6���Ϣ}b�)O    <ŕ��$-rɫ�I�/#S2x�rm�Q�l��s5�z�fל�� 2�~�f߿$��,�j� Ϭ��r�"f�'�t���*��(������.Z�a��<�q�7x^\J-c�2�b'O��93ye6s6�E��I�񖟂�ZoҗMA@4�|7u��p�6��F�`ibϋ��5�FH|�;8���,�����$s=��݄ H� �o�N�PX���V�A�ӯ�s���:V1�ǘ�>�1�%�m���t�ZEm�m��d��N;T��$��3*O�sw0�l��N��اi�Q�fk�I^g&�P�1���Q�vO��5��!�*(�;�"ތ"Q���s�ǒ"�o�_�2>%׎rU�&Z�lM����=ܭ�T��bO77A��l6�ve����XY��R&�,�Y��mF�H�����aۍ��Qۗ�̂!������fé!P,5�,I7W��-�R�w�!ڸ��ݨA9�R�Q�����KZP|J��g����HZ�^cw׼�|�ƅ��Kz��g��oiC�Y�m��8n˻���z�w �	9���ȝ'��E�� Mu� �K�����e���
��3a�CS|'i�Q��(�uJ0�Ɋ��+��_MuZ���s���N�)��*?��TL��	�0�����0�&����|Ru�J�x��,p���)���`�:��ܻaZO�M�~�>8���U_7�¬�
˶��;H�=���Ucg�-��N�>��&�̄\G��ry{��-X���̻�
��[?�+��s����Mp!�=�%����u��/���G7;�Kc�0��V�u��u�}�Ǫ�@�Ke����3=MW��N��u�o�O�cD~v��̴^�8`�Mw��|�D�+d:�	�-�v9b��(Ԇ�&p�Ui߼kab���=!
xz�mR�Q���Z���k�s�N�Q���u`r%��u�[I*nQ$u�j�m���28�G[���ͷg7�Ӗ%������Hg�d�JE:�Űd����q
�,c�<Չ1�7Ճβ����2s�V!^��ȱ�l��7��bHQ�uY8ۡb�l�A.�}}T��j��K��^�/Q��q�?%����X�p�J���#��������V�� z�F��}5Ul�i�����0�'�+,�
Q���$V
c�8V f����6���ج޺�Kvϐ��`{n�H":��xXь2i� �W�W��e�Ď��?��n���Йs�e�*Z}�K�!�XY�̂�9��e���_���(��p�ׂ���:�I�@X��~S�t�i/`����W��s=\����0`0�;US̙D�����]K[�������6;6��=�y��&��˙�yk��,��a�S�)�I�."�����]w����S
My@�$N��p����>Z�	�
6��p��̔�p�И��Jk��<����j�F�Z�x�f���ݾ��U{l.9#�q�O�63�PD'�jo�D(a!�'Ǒt�XG˭��j�ɆU��|�����`��D
v�Ne�v�q`��N��og��=ׁ�����G@���h�03��� Kz�l�aXk��&�H/�l
��.Nڭlb O� ��i�a��Mwڜ���j.��x%�;D/��e�ͷ�z��L��uE��Z�(
D�_6���xx����`Z�(�ϖ���L�V��
��'Vժ�M���e���o(g�%�1��Sg�nf�.���/��H
��mQ-�M���2a��p@	I�ʲ��.Rv���Mg^�eRqD�8mB7?P�4�Pbi���0(ʺ�*�^U�Э��ۧ���~s����
�>��-/���C�z'�QνyD����J�k�X-��0�4��D��`���n���ad�[�W4�8]��ʩ���#�D!�������Nh{s®�,��nV��
P|?�������Ռ�ʯIU 98ߓ2	�\�K\�&񂹛 �.���.[�
-lN���$�\?V�c�W�ˌ椛f&�B3G8�qn�Lz��v�F��A�Y��9S����b,j�`�nAUPW��(�t��۷N�J��L ������-1�X��gE����<��"�����8͎�?o�h��q��)�{̞�nX�y�iy�(j\7͢)��I�R3Yy�t�x��|'����\0w�����+.�Ct����](��;��[T� m¸�Te�M&{K;����N->5sT{=+��lt)�� ����K8:i�8�V3�IX�-TF�S^ZT�g'���S��廚���u� 9�k md��xc�Ap�*�)|���޶�Q�܏'lt����J�!��A�瞥���Y���ᠽ�wO�!sz2��,��=h��� .�_�>m����}��*�O3�+ub6fVU���3���nMm�G��#tDG;Rvs{`�u?n�z��lY�k*�f����2��S5I�WM�M
�"��刽|%ߪ(�co|�yu��s�~��aENo-�#�~��ޠ8�'��x�9l
�GsC|�Y�w-%۔WH��x ���Kuu��}�c�N�2Q<#���R�g�홫����]K�	���R]l�&�"��t�̩���`
��x�A����jM�ARh���)��N�#}�GЭ�"w�y/v���w�աf�rr�#�|&
�pD�%�.�<��EN^��c�خT���W仸�z�D6/Q5[��g�<՘e[��vn����f�X`F�EH���n��,�ekd��������z���F aԝu'�p�a���Cdu��
�cJ(*/�
O���G��%:3m^�-��ƫIld����KX�T<�*8IL3\7,���)��M=�_e��l:Q���gG�q��X�ⷆ�EHm���o�A6�؅r՝f������z��� ($h�U9�I��N�*Xd ���8�c�u%j�rԊu��{��}õŇ&�M3�\��]__���΅f��n����uo�����DBd�b��)?5Ғ�����
�T蝜�R�:�i�UQ�;�x<K���I��X*/��Ң�� �����^�_/�`i�>�0h��m��Db����[	��iY
1����t)>�c��7e=p�o�RM9�
g/�v���ҙ�¹h���Z=.+S�kf�Y�Y�,�o�`�E�nn��������l���84�Ρp��q��;��ߐ�J��W8�lF�_��&߆OHu�"L/�����Z�Qg�z`i s��9���d�7u��k$����j�~�����y�%��y{���p>w}E5��_��t�l�٨�k*���/^���:���i�Ap��]��9OK��P\����^jm���g>t�l:D��jy�Jd�Y��R�/��h����J/�1��)���CM�6/-���n	��3b�ᆾ'����2+���4ūh�;Ϸ�D�$z����X�iLݞ`��UT�y
^ؓ����t�}�=�ko�a*�bfKն�m2�n�̵7�k���|�X��$����!NH���.�^��e)��Ȕ˯���Y����H�����nDx�|R�%r�%�w���C,���s��{��f�h@��]SEN��*�Ms-�Ix~Q/�~�����s�e�\��_��?��������,TF��0���!����]7�C5�V]b��"���E��0b*}
V�L�p���Ɋhq���E��ry�i���߽�{�H5DiY&� �P-<?�s9�eϲ�G�2e�[��bU%�D ��]K�C}<�8���r�[]߭����ݗt��K��B�0>���C<��%�[+���NУ�qk�.���;�W����JqMy
�FG��j���w�1^iD�J�{]��Y���Z�feެ��$H�._j����l�G�y�Д��D&�$�<L��x�:�#J�	�n3:���\}YWJUϴ�ʏ<���kS&cC[J�idM[%��/�W��{}X��|p�q��GU���4��&+���[�v
�����M����Y�UqTh�w^/��,��t��-����Ƥ��'�]>u��C��H!���n/���
�K��j5��C�}��	��������M{ä>XM_�דb��{�MI��%���nq��--<9�e��:��n�.��*�q3����9�՜    �.r����ms�9�H�;X�7'�Э�W�R�&)$�J�ɺ��+_��4�-�v��ܫv'Ʋ��!>V��&`9���E�H��|5)���GC����t�m_�jRm���7.{?��rpKةMXĀ�3�"3���bdώUc��g�"C~�ܺ'��]:T�AB�c.MIŸ�������Jm>p�uc�fP��m
��cq��]�^0E���K��Gv$_	n���m���zi�d�O
��]>�:�`>�-%�'<]�@(&��`�=�X��t"�/�����  CKH<ǡ��f�� �����^�=�m�4���xZ�&˘��>���>G�1z	����������S��"L�z���?7��H�p�0�}}������r."Z>
�ӭ�ІD3�t���\/�0�Ew�#�_�Jd��6s�j���#_���+<�ϗ�ׅ�c$�MJ���\�
��&O�j���/ �
,qP�G��E�����������$�dd��KK�hM�Vk�����%Kʛ�UPy�_p���Ь���ܵ};�6�˪Q����+�}�Mt_>�g1�<�U���Yu�U �³2����iwb��T�Y�|*���H�T9ө��JJ��ma[�V���>zV\���_H"zP9��J��`��8��lU��r&��XJ.���z3�4����ܥ	�k��g�0�!Z�B�%?i��A��3�0��2�O']�H�4D9C�FW�"�;@!���/�)�Y��|�9`��L˦��Y)��}�ػ�22xo��L��\<Zm:�nO��>�����"��7q�Z�`S�*k_W��(aeq:j����F���i=�/��G�q8A�>�<��Q+�������$�)[���j��W�q>.C�� {�����3�d*����X�f�'�-�'y��|2��0Qi���Σ �(��u�EF���;UE�!��,u��*���ua��W�.\�U�y'�}��9�{
� �E�`��� �_w��i1f�/F@�o&>��K8�[��?߰�h�XF��P�4ǧ���.6!7�@.�7�B�eZ����q��*{h�-9��a�3��XY��h�;�ϑx�^X�Iw5��R	ӻ7ipS�E�Y�%�ݓ~���6w�u�E���S��<�x(�?J4�hg,�0%��(a�N&�������X���n����|8��7�E_��FS��.,�R"���x�t����4�l���'�n�ӊI�2^?v�첨g�H�pٺ�.���ܪ<�Ն�}��ܦ�^�wDP~�%;0CF���H<�5U$����U=���I��f�J�&D�%7�jzIc��B Ͽm@�&�(��^���Ft<��出o�t"=���݈��e�N�m��&�Zq��^}��Ym�:^y�P�h�ibv��樮h��Ǵ���"	���+�F�����e*r�n��6��b�DQ�<���*��\
��VP)�ו�M0s����ц���@4
!�X�H$C���VL5�JB;��]����l����~C��ן��)��ᖅ�l��ħK
ik�+���W�R��F7zo��W.zB�"Pb%#�Ju�dQwΪ0
G��aÚw ��m�e^[�[���T1��@�-��p����O�Be屩���dxMuVt��زL��
����t�ƹ��em|u�io��L����=�IK�������F{X`�˩���*#���,�0�������Y���[ؔ֜�6�8L���V���w`��s�uԭ�!�D��
���Ӡ]��r+��K;���hf��gu����N<�D-{���$U2�0������~uԺmA�gC�9W��,�H�n$EѲ��,6�6
��Un��Ve:\�E6�� ]�_�0e���3���:�|��[�����zg%���p�;\�{�b�ݧ"�(gZ�� ��$��$��;-��Lo�H,0���l�\$|���K̉��
Ҽ_��Q[��#x�����w�S���Rпs��!CPp�M�Tm�:a�{�mbv
%���������;��I���*�A�a���U�p[��/���u���T�MJ�x,��˚�r�����x7��c���!�s���\D96r�&���;H�j�}i�M��5S;�D�/���"��I��u�,[�d���0E����� ��b��q{�Ү�W�f����01�ǇU��C:]R9������9�[�^��a*��y���� �1�������*yֆ�]�K��=s*ヵ~u]6������v4[��t5C[-q��T���2��qa����Gw����&~h�jJ��Tb¯.�ZJ������bj��|����2Q�=��xȸ%&�vK���,9x|��B�5�o���|y������-�"_]����Ą6G���VZ����ma��N;/ɧީ�e�X�V���rg�F�`W��̓���T?M�Uo茆k׵7�WR�i8�"o��7�H��0�	ݙ�N���So����.�Pһ<�{3sU:��ر ��-"MwE�1���������H^y��&��y-���UR��
t�����n��4.�
sp�T�w��3�Rɐ�lb��@h�y����QFfW��y�8l��.�1��~�!#�}�$T�����H�&��(���χp�diO�d�E� Ok�>����T�P-/�W��R�|�i��u��w�%n�����]�[ʦ�a�L#�G�XN�\�w[zY�������D���*M�V 3PRVnn(�Ϙa����#L3��5s��e��7�!�^_
�0�tK�r�{Jc�PQ�}�҄]U�(e�N�H؟][���d�Fl	��y��c�^V�a����S�wd�z)�Ht��Ď4VsKhz�M�{��wC�^)�|֔��һ��?�����5� ^Y���s)D@�= $����KgU��"[��Ԃd�\���o�|�@<�Ք�E�OѴPꊗ˹����dSb�F%O��4e�\���
p
�+��&��š�#x�L+��0��ce�lTo�ny�IT|[;Ծ�&��$���wY�L�d�hLc��f���j�sl��ɻ�ed1=<��#���	b$�V�P�(�&�#�|Ws$���Y�k�:c�%��4�gN1�N�\�-����
�oJe������3y׶+u�e�CĦ1��R��O��}�X\^�X���(�z��rQ��N<�������6-�/��'�e�<��B���	¦�^��'7��^Wn��z�Mb�����}i�>� �'k��kZ���7h_��j$�`�i�W����S�j�]�H�G��|�zF���i��5����iM�*3"��v�t�xa��}�<�=�O4�"���rA���n9����EIP��-��K{5���̎�I���}O_�xb����Dy.u�
��6��.f{�M��6_���
6��C�t^���H"�(���)7���%���5�+L"�l@�DbY5��j�ؼ&jx~6�D�4V�x��6IT1Ϳw�g��� K�%e�"�[+���;���ʽn���W�b��\�h��,�����r6���-kո�a1�F^g��V��9�b��kj���[�>x�?e��^T�a��U a�W��r=�/���{����@�3�)�Bc�а���">��r׀����6�c~�kXI}��,֝E�Y��Z%�X��F���y�=��1��K
��ZnU]�C��ۜ>��q�b���LyG ?�M1~�4>��/�Q��2�� ��TN���i���5�O7��SO/���jE,�ҩ.0��I�<u�7�kJ�yrE;�D�T�V�[�F���^������1~��t^��<����ɗǋE%��FcE��%����].>���s �1 'Қ7m�C�s�lj/7\姖d�<�*UC�&&t��)z�U[���g'�!f�s(�L��)����x�-�-3��B*j��x��|����8a-:ʍ�]Td�)&j��нl's�̧��(t6#6��t�c'&K#=O���[�a�V'�֦�K����s�?F��_�����_r�� ?���u�`�ʬB�sz����>��Ki�{.��-SyU��U���	�T��T^�������H��{k�ӿC�IŸ�J2�f�� �õ2�    �V3/l���5�_a���5{�D��2/
��)O�|F�]\�kl\�����U���A�"Rd�ٝ�g��x/� ,~!<wؐʝ�ݏ��ITRtϰ�r�^˪�7�_U�<�>������>aӥi��R��oo�{��;vLI�F�c���O��-��m�%�yiԇ�b<�j����51��7� ���X�l��[Y�I�;�߫|�YT�B�|ϳ$���r[������۬�Ԋ�G��|�3i���ͫ�J)��FUoY�����K
�kK�fKE)��Xd.�|��/3�m7^�
�[	L X���M�;X�B�p.����x�Y���Ӳu� D���p��G�Syܧ%2��f��v��v$U����ȏ}-�d���KumP1%��h�ʯI��b#J����?'=���*��}���r��M
�f�����Ѧ�7,�N�����o	����eu����0�{B�'ǥ�$�za.�b�y���s�@���)���@^PPr�j��T­<�W�m� �^<^3Pj���w�	��@�)������~�R��{��j,)6���.��_8�B�l+�t���2��r}�H��eK؋��^��SO��e6��N���
�\2�l��Ji�f�m�Է��y7(�,�i7	�/��>f�/TRX���Ӝ�-�jˁYlisݓ=Q]UXR�4�)�G��� eC]�S̩���)�����<�U��������X�6�L"wxO[�J�r��YV�-���yo�"B���ӇC�� v�EV�)�=}'��bv/�b����'\z�̻OR�u�4�U��'v�X4
��+o�JC��
G�M!3�z���P������a��S�q���RI���*WKl���k������v�&>΀�O��i�"�Y�x���$��.C���4�YBJ�C��O�51��`K���I���v�����8���7�}^��Į駨'���p��<>P%Q�H��|���4���
۝tw.ڹ�s|�e�O
~�So����"7%}N�P�)�l�O��~���tr�ǥT��������1x<&�_�>��r��Tǃ�fP�&�E�4��2II��@�_�b6��U0�ߪ$�%oFU�~� ����b̮xح��A�=jE1yEF*\�)�| r��fh�
�n�B�r��K�c?Wkݥ.�d����GFJ��xeAb$b�AӪUY����M���u�ً�_��O��D�`�"+�����~��ܭ
��~�QY��?]��C<�� ������z��0������apgYZ�^n�şM;ve؞�pi��N~j���(l�U1j�Ir� �}
��#$�>����Ys~���}K�V�9��ŚǔbH�B,�K���p�ic{T�햿�v��u՟��0ճ���X�j�=-�k��ƅ��l^��]�:�c--֑�d.5jI�s�M�HF:y�P�	�!�/�����s�8����^�&>���E�[�trͧ��\��lw��:�H��z�e{&� �cQ��Ve�(
y1tI�#*oA�&�V)�l'ω|Pv��!�җ���M��4�ӑN�dY4���,�><�.��m���ۡ.]qy*�k���r�@՛���W%#����Ѥ8�L���欷�qm�;:(Y-�,�;뚥~f��Yc]oG�մw?4 c����I~��&g�zY�@D�3&S_�]`kgө4n��^�o�eW�>�~�}�ڍ�?Eh!F����W��Ⱦ�I�-,�����s�!./�p8��E�pr�oe��H 2yr�5�譋>���X?��N��?���o���BnC*S�J4TE��_��)�S[w}�f�W��9ղc�6>�Y�{&��ĞQ�dڈ���¤�����ĕ_-b�OK�*�߆�%Q~��2J=�����:�{��[{O�����6�[�Ar+@�K�;�)��_�� �Rlh[�0�+��ū�w��q�qf�
B@� P��Rߘ��)R$�j���J��Sxxͳ[�G������}�-�6�SԲ�@����$��j���Sږ�ß����m�$�ߧ�/_Y�г��m�xN�W��
 Z/��3{���1�jd�VC�rڶ0�_�Sxn{��tt8�n���@��>���rg�ҵ�!�1��'��x�&-�6h
*�>D~�^��P��K0v�<Ō}�IS����Y�wj�jm�r�R1�.���hb��_��K�IAQV6iYA�y��<N��ǡz�\.�؛�ތ���/c[ޱ� �I��e<?{�i�,���y�+מ��d�Xϣ���'h�� ��LE�����:�(��K��m6����KI�z�HR�MA�G� �߱"5��?�ߩZH��$��TU�Yj����'����;���<�Ȍ�����`�'�$$�7�����K:��ܜ�m��q��`rP���/����V C�LS�HQ(�&[��~w7�M0��ʷ�� �-�� R����]H�/4g�ݦ��s�z
u�>��&I�g�4Mf_T3���i�)!�'6]I=��g�[������7��
��⑜�\��m�l��s�z\��^
����s��Zf��冱WLl�+�W�E-���F�����q�G)���#:'d�+��q?��IqL��MU+�Qi&�[�Y��3�c@����X�e�Vq���L��!5�v�~6X�(��f<��Q4��P��D��Q�k���[Ur���o>Bg����Q�~a^��L�RQɞOS���`�/f�[�� �6>�o��~�ob��SӘ�
���M����R<�]�_=��R�ITey�&����<��g��&̥i�U	.���zu<3q�oq=����x���c|��,
MS�����]���TS�)����!��gEQx��9�IdST+��U^�W�w)t�z-����ί���e@1��+�d�sLI�G���n}��D��kU�K�o���ے��A*EƗV%=�Ԁ��&���|j�,��L%<=j�M��|'�� J���D��~X9�
a�#��YM���}��rfӋ�Zs������3�BWj� �����m��UK����
�R����ho���BQ5?P�Q��	"����t`�HL�P�W���F\��ś@����ɚ�!77�a��\]�^�b���h�M�S�v������ud���WB�9�����?�v-��u�Rӧ�n΍�vJq4L�G�$�if\�����V;ft��cvj�@�����u�Av���لF&��%���t�Ck�:��w�:4���c�|@G��?��z�f3=�y��rY)Yk\V��M��wA��[	���\�]L����\�����U/x���|	^@쾭����B��/4q��1<���xZ���+Σ5�Jc���v0ˆ�J�E��TtI���<=���nom������U�����I))���"f�[T�5�k�^4wݽR�z��d��*M�^�9�#���^q3�7&�)9��O*��u ���=�wM�2�-��=2�+�>�Ҵ�-\��so~��B=r�G��{#�-�6�4s��]Пޗ�It���[��y��
��$� ���Y��1f���b�ԓk:0"��CV_Hs� n��hz��-�k�X7��z��-���6u�2l�\�+��w�V���O�C� ���p�:�v}�[�ZK�4=��]��!.-x�j";��Kp}p}�i�G]O��[q]�̨7[���ߙ.}�2�Cj%��`��Ep3M��|���),�-E�ڗ�~>R+����Rɬ@2$��G�Ǹ����j�ܫF�
vi��T�����j�H&��������Y��rWU�`���#�6 6��[d�?l��z�_kB_>ӚqWL�$��«v�.�x[����9F�0J������V)��uÆU�Y���&���ԃNu
����-_1\h����`7W���H6�����p��'ڵ�2����ξ��Z���h�G'�q�цT��]%ѧ=�zNl/�jP
ph`��CP�-�Em����FA.��7l+��n���!�$�����a(I�A�T�K�|(�ͦ�,/x�"��kF�ŧd M�Q���n�Tx�k�    8�z����nܕ�?�i�)0;�3B��$XF�|l�K ��Q��6$ō7��
��cgF���b�����������}E>���uZ�%��k�Ջx���K�Ҋ��^�Mr�� ]12�� �pE]ȝ��a0^,��"��`������O}�'�5��a�(eJaj�vvԆ�N�g����ӹ�������< �^�{�]Ĝ�[r'�$c�\�E=xi�@suo��N�'b���
k�iU\���y}��1�_�ZcH�Cga>CT��lod���Yf� �Z��$ޡ� ��&I��ӻ����٦(i����R��ՠ3=�x��x��;v���Nd)oZ�rS�47��j���[��\���)6� F����Gh����2���>�	Y�6~�r[����G�|����}�Q@�/��Փ>�-�N��޹��4�q�%��1m0%�����>��S-3S��b;��Pz۪S����(7q���T�~`Q�k���#��w��q��i|��q�j~R����oi�Z�������t$���%	�F��^���J���~����3�;�
͔'CZ=1����C����R�A�ϑ2����Q��WO.[�K��U2��2�+ ��I�����H�����P���g同ٛ����ǷX���
�oc��"�~��sc��pR,��}|
9��waEZ��G�4!1 �˗%Ӌ2Q&vuQ�5��iN�!%VY囿��C.��f�*����;^�������֋�'���+�sIvH(�VF<��B�8��S%��1�G��7�ſ���������P$+KTX���h&w�h��:���jK�����f�� ����!���l��SL�s�-]�.�Rcun��\�rXhS�;:���J����N^��`�� �!
���vZ(�N}���ѥ`b1A��ã�Z�zfSL���,H��(�U\��F�֪�s]�^�T�9����wb��6~b�C�U�5U�-1v4oi3^h<���]�ܛ>���ß~�����������ݨ3J����	���\�
��(/r�w[��i��L�P}R��f�Ӵ`��g	}ѭE��ъ�ټ��B���撗!E|J�R���*���[9S�7r��W�`>;�5\�|C���f�� \ٌb��`ԏ�=|�j�[���rO�S-�c��zﰰ����Ք��'E�~��}%���+Mv�AN��R�t��e�4������oj���Z�,�2L;	����2��ק�
���Z�L���(�(�"��*q�x �H���(rgk/+���bV��>���`B�L�;vSAY"����0��;��U-�N�`��:L�-$����%:ѮaKyy>܍��V>y��?u����WK�]�R�dM<>�	��{����#.��8v���$46�*{�rO���*ռ��TH P:ѸO.�v����)P+8h��������Q�VS��n����t9_��c_g�w/^;Dp�Ե�C�o��ӢY����,z�t����w����2�r�p���xeۏ3e�P�j�|#��E2����:��ʳ��N���խc�ԭ��� �¥����=K}���
wv_��[$���F�?����V���lO�$�(��l����yo�����'H�N�/b�iRKQ��2y'�M�r}˴�B�w�0-�K�~���v�K�o�ס���% ��e(9P��&���k��;�õx-m[���e�i/��|��y�\0���V��U9���{���i/:I�di�
���*l�Ս����K_���R2�Rix���'�fa?y��0���9��7�M���XZ�ƈ�$�깦7t����
ȡ,Z������n�������0[$_n�?W9^�1�R@/�5��pi{* Gn�3-�v7�q��!j�l���E�&���B m��Go"j����	���!BTTS�ys���ž�.(�k6Z�(�<��8�G���s�b��=!�p�CѤ�1.+�G�����gop?N�q�^��"R5b�ؐ�~N� l�W2�	r�z�@������Lo_��r�c�oC'��V2L��^��k����U�]�6�U��g�X8��g��+ y����W:�a֞�j���������K�/K�e��JH�C���4M���,$��)6�r�Z6ı���M\��Eڮ'�7��f�2����J����z�S�8/-ۛ9.�+��v^[MmU�k�V���	���H	��C��g�R_*�q�y����׭��R�/�;Y�Mf��9��tY(vk;�����.j�*`��	����J�|%�Ba{o>l�>/���뛬��5�ŷji�0Z�M�O>�խ��I�LF�u��|Ǘ���u%������ZP�dH!�J��_���+����KcQ�����Q,���2�/�đ�'j��PU��l�]a����o6]}x��pq���|1Po�p �*j8}��/���Y�?�r8K<�
���&�A �6&b'�s�֕L3Sc�I2 g籓�~�rn�W�}�wg�p�����L��(hJ��������[9WRߦ<H��I`B������N�i�M������>�`+fw�8��avh�.�>��?ɂ�Rk6���	3~RT##3�o�ѣ��ൃ�u�ȃ��W�*>A���;6I��&nu�k<�˵	�K׏5�ʭF�̔C�o-�M>��ƛ�W�%�\�;��8��v��H���3�[]�(T[튷��^�~¥PA�.��i�����1d�L±�lh��^k0�*J���^�B�~��4w<z�����Q�g���L�6	׽��Я��Dl�ۖ#-t��I���bN������d���zp}R
��q�?��m�O�2�6(�û)1���݀�fet�k��B��p_,6ѵr]4��h.��[I�+2���L��
�CO+X*3aJBm^�ՕV���M.{t �.���C�6놪�j\�W�ڀl���	㉺�}�Tܸ�լ�j7�������Z��q*�ZK�Q��)U���Zzj���DL��c�W�
�d�S�����F�ݒul��5����l��=��$�f�f]����U/ղZ�U\�xÿ�� Xgh:�2����@�2�T�Y�[/]�r�s�p��.�vV(ͫ����ٱ�h��9�`�C㹖�z���+c �-!i�$�Y�{�Jk-�䧛�p�dv
ז땯�7�I���.F��ڻ�H��Dr��U���g
u���QږC�VnX/0���U*aߴ/�+�V��.����vh��m��`7;��a�7�����Q&$�P��5��+��-̀ۃb�Z����(�����#~�T"5F��i�)����3�ʳZ�GkUhW0�������~~�2&yڻ���ɿ�0=Aaᜊ��X�+��k��y�2b>���?�A���p��)A���h�.7˻g�Yo��"k�dU�C���ab�U��Z#��=L$I佤v��׭,�f}t�X}��N�1���hU�F�T-H��~X��
gm����Qosk�ý>��@c5k��K[1JI'IT
�i3d�Z֎T�U�����d}���V�)������b� �{j.#t�v��+���&���pߜbܛ�g5c~�r��1����K�4�j�4h�����0'M[�V�U&J]zaaӎx\��4ir�Dӊ�du=�R|@��,��JU{��	p�2����P-K�G�cT�n
������e(oN��4�����V��3��1s�WaZ}6��/�=�Yt��<Y����u7w}�M�v"���G��y�;�qN,��Z��I8�E:�	zC3�ޙ���J<�rg�q�wVL��O�VxA�78a�SX^մ���&�ʯ3�Jɯ���{���o9mU�|��2똚��l�����A�W�q$.V�_9�-\��d�w?�F.���Uq�{�G�0K�W���V\,:E-7}�6 ��i��^-�����V����{�ԓ�j��`���a�����,w�!�"U�#
���!I8���A����ر��lo�t�'���pjU�NH
2���tS�t`�i˺7����pK���E'�*�    2@l�k��GZ������rz���9��w��X�������T�&.*QcN�4�栶R�(�Q{zZ�<-�,�֘�f�m.�ֿ%��%=A+��E���ԙȱ׋r.�}�h�o�l`�mho��U�;S�9C��-D,}�0�P��[�F�N���xC��tCG'���H�����$f��U<!�
��zܶV��x��<뎅]~���*~��b���<r�D1��Z?nEͶ(�����3.���f*�N��Y����٦"��?9�Ҋ�fl�8�����a$���?��Wf"�e\�ɱ���C%�A�u�I��R�~Zʓ�3�.6��O���|"�������
�$q�.��`U(d���֔>̯P��!�xF~���/&U����?��q�p���s�ib��?d�� ��.�d(������ܦi��Ǟ�ϟ��Б�ѽ�8�T>�S���r8P4s"�b�7�aİ雒��ݩ�K��z���c���?uN��g�#'^˰NCw��F҂��I���P����u�Y����%����\�� ՃK�L;V���` 5�tYΎ'-���h��bdɃ�0K�z--쎖ۨ�H�b�Q�4�s��[�il�08P,)�to� l����6�v����Sg�����[q��w��b(�P�E-c��w:&P��7J�Z��j�����ua���wZLҩ�!�f�>����b4���C�u沅�V�2e��c�	J���oJZ�J=1H>P�n��n1�.��3�*�5�Ĺ�d�w��j,5>ؑ�Tʳ-�syI�VPXW����</c�܊�4��T42�E���#&r�n>�e������v#[(���}W����$/�Kد0I���=޴Y��|�8L�d-������m��Ǔƥ�׸����U�D���c��,YW�t���R�T�Z���y�_C,�,�c�3%<��=��V7���ҹy�V����G�j����}0�<�F�HK��/�M`غ!Է��>D��g�L��
�f
b&w�ޗ���a�K���%U*�j��f�2�3�"k�oehHlǺ�����<X�K}�&&(bsmCf��_%]?��Sd��y�/F���$�Iai�_���G��8��[�	n{�ݬ�z]ė���~H�c~�����S>�qYo�w֝nnxP���tqq�;�t�U�W/w�l��R�\���aK�o��r���䷫#��&��eS�3F܂��&6�X��F��걜�������_��=�`۔���:���L�CQ@�Y=�"o���+�ԗAe�#J_?���0��<:K�:���ְu]�m�to��JG��oZڠ�b�Y���![���PT9Q���#���\���w�)o�X�z���JK#��<�ޮ��:/�C��R�*.�
���7���s�V��:W���X�?�
$�k��E�~*�;������ ���C	~/��4>.څ�W^� ׵�D���Ϧ���ё=C�e����E�_m�w+�(���г�y�����*�?�� ����HB�y�`Ts	�y�XV���nh��d5.�N:��ߌ$}�nd�$��c�.K6%.�$$K�*UC�z��y����vK����ClN�t��VU3CY��_8o��B�.���^�S����>��0c>���9p�-������P��j��In�~����y)�*�p*�/ j�������f�����]ы� ��o��F�b��T�0y%PefA_�,Oж�co�P��A;�;m|��NC�U�
��ZAG�7��V��r��X�I���,�5��)���ͼ;ki�u�y��Ю���J4;���5dN�ie�\�r�� ��&�,!K��	���t�$���_AoT��T6��h��-E7182��G��;ܫ��n�0�b�D��ꑨ�H��UZ�B0t|o�������5-�����j�F�
�r�|=�O~ɚ����/ 0et�Y��Z)0��gǈ���@Du�X�/Զt/콵�(���D�}b�b���`��0oD�4 0y!�Ǖ��"�2.��p)x���`�����r��"$�w7�Y��V�)��-��|�v�Dp�I��|�xa�xj��=8�S�U;[����L/Bm����Y�RI�qRe�Unɫ1g�����G�_�]80�č��̋�8�CD�
���
�`��~��9�5P��g7��Е֕̏�PH��A�n���-ܴuc)�����jw3O�6��,�'O�òyE'ǖK�rH~�/ ���|�zW����)�;]d-�*�]:O7���,�qvd[��k30�Q�^j���W��B+��)��MbL���w6U�bN� XR�	ŝ2;mj���v*�Cu8Vpu��>�[)iØ1�,Q�y������ B����ԯ]���'0����bO������J��jƃ����D�����,i�������[7��|G���=�%H�n����.�����í���U�U�4�mzq8�'�\K��H�F*RK�����PX�K�ΕO����
1����8*�5�Iv=��m<gA6�>��C���a�P&fǛ�R���U5=�)��s$���>?�\3[�������E���.
0ajUEh�6G��_�������z�´���D)[է?�+�d���qXD��#��I��z���e"�l�X�K�]�;G5���V��L�<���I$UI���bF?�S��9�ժ��ؼ�'��%�*���α�����^2,5�~Di�_�q�N�lH�y��V�\��l��lP�(��f��A��N���Mt�Q��'�s�x��~��[Q�3�7~lO�&<튴]�:��)B��\i1��}���+8��=z b�&V��`��_�(��V�Z���|�Y��z6>'�U3z���n��q�tC{1���)b%��m�Q�,�=��ħhRV��2a�A���l����$�F݇�X�kO�9�=�K�>N���.)��$�4V�	��gCMR����([�W�ڻ�j���`�gbU,�dJ�3���[��G����ԙ��f�������n�l��w'�GRU�Rxd�sx
=|��bdF�m���2�����ĺ_M�/"���ʙ�y�b����q��#��u�p�5��N�{Eݷ���RJ�� 0���ۣ�����Ү�����͑:X�����d^�$�
��I$ڋQdy�g,K��G2���h9bΫ����0<p��,�W1�w�r�.'V��D���xK�w_����S�W���a���y�i$.eP�4�!�����8�|�m���mҐm���$r�+|yҍ�`a3u:���l�b�'hoY>mc�,�w �A��b
}/LQe�5C�>�u��հ���l~�Bq�l� �r��o��rO��ɯݝC��&TW�tS����r�ޣh��^5��z�)��IM���ǚ����j�jOi]/��M`�hP��-R$$ �����<t�p�@ 7ZӠ�R+�b׶���M�-�e+�C�ih{�*=~��B�V�JU��u�b��8aڢ�ߗ���L��$�!W�����0���
�r��t׹�\���ҋ��
=��&g��n�ш��_�����z��F~��tZ�"D��Z���R�TQ�;`�==
�
�/LEmqlX��&�fC\[�tb?!�}@잜H�
��Kکw?:��b��-��m�c�c�x/�׹�W�/bj@����(eΘk_7��v[�������\_c�h��x���3����%�FN���r�������?Ƅuۧ�'tt��q�a�[���FwZX��:�vR}�7+V�ޣx�f��v��$�չ9i��K{=���� �#^��z����}��<����
Wi�[��mz���ͩR�U��u�eϸ^qs��MQ��rW'|(�d���ж�-���3\Z�JE՗�K"��za�f�cR�~k=V6e��	�w�"X�8��p��ߏ�6J1�?���&�bu6�w}��>���5�hUK߈WC���a|Ҟ@M�K��<�a��
�`�@�35a��\�p=�C���}�Q���燬�<�w�
�4�,�N�K��8�j:�x�!�Cg���T3>�k��_J�|x    ��t[=&v)؄��<�'N� ��w���>�̜lp��-�'73e��dRtF�k�R�z�����;�׻�Kj���f��(ē�eS9����/t�N��"o��.��Jf֞�3�()��_Q1��D���5G�n�Yu�KEDx�sl�G��}�{%��/gTF���k�K[��R�^�֤>Z���pM饐�{���@���Ac:.;��%C�>u�xe21޺� F!?�K�m�}�zYX�̆�����V���0?)��ҁ�'�:�P�f��st����N[E=pm�%��IQT�8 ����M�ib9�[�x�hX���cgBˏ(��&[��˙���YƝ����U���������%{�����F�rUDu�2�ddԷ�yv��|�����~g�'�#�`�WM��'����Z�����N�)�0�LÒڹ;��-�/��O� �<b1r2.����pռ�>+�Uԉ2L㻦4iӊF��lic�Pl�8;(,V�����6;�)���ׂ7�~3Fu$%9A�-L
��G�(���&5�{��V��a���,���Up1K��`��W��)�h	���:��n~Tќ���@�2F���$F1��3M@tU�2"W�Ë��IT��'y��-�ݰZ�-|���]�7�B@ؔ^#3�P��p\6���v~vW�#?zN0���?�c��G@�-q�%ם�5��-����j�Zj���ɽ �2P�w8�����fU��ȿ#��~gծJ�~���#���oC�z`����+��D�����M4�{Fe�}x�yP긬�"z�O�
�B�U/���G�x�������r֡s ����ex����X ��H�Q�����<�mb��u{�����/-�pT_�rFx!�����SF�??b~ ��x��>UI�'k��T>�:N� ��b��7�z2IzkWZ�� x�����P6R85�ݐ� �&i8�#��{��B(��L~�b��Zf�G�� |�M��-��\��E���=���6E�E���J)o��**OT�#��Z�qe�J��j9�(}�DB����'r�̡���낡2�,;�Rs�~}S�6���
���#G�zk�a�	�kU&�u�=����J|kC�ӽ�s<��ʛ,�K�j�&�IS�d�y	mZ��`�֣IZ��X�����#Ԍ�� \"*Z�<�,�
�;]Q�f�8U煕�Np�OL�W2U�l:���xnD�螔��|s6o�3
ͲUu���1��\�Ac/QGTd%��W�z����D������b�e;6�^��HJ���Ae[�6������lZo̖�>�|q��2c�*��#�K�4�g�O!�e��,X7\��������
`�RVN~#�,��c���Tץ�.�1N̔����)�.��XNRTYxee�誘�V��K)�L�T1i�rJ�9.�ޛ�L×JX�-c@*�%~�� ���n�ț��۾l�ywX9\Z�n?�1�4ț�vpqU�̜���P����z~V�
k����v��eӴb�X�Qe�b���8�^#h���u���qP��|���5���P�+���R����ԜF:E�^�+�������4�~�����h�(Y���֔_A�O�Z.���jf�jis5|����)�.e�"t�
�!
YΘ~�t5��AP��E�lN�{�������O��	�E.ur���"�h9�a�}���-����XW�����W���&�Y�����l.�#h��?"30K�PR���^�&�kԧ]��X/y��R��M�P�6�� �5������2��x�$X���z��P��o��K}�u	�\��0:me��.��mٛZ..}�2ɔ�T߷��BR���ԡԩ�Y�޷.W��xm/u��O��z��u[:8@t��bJ��z_�&�0�������	�U����?*�(�����Kݯ=x�	��̖��<��5#O����2P�7�F��l����/J �����A�	��7)}d'ғl���������KS��a��ڦ3��lcZ����?)�4]�AKIR�w�3.ѵ���}��k}�,�����%�8���牒��H��t5:�����1�D����z�0I%��6i�ZZ�h�`Nl� [��yV�8����[W�)d����Iz��e��P�B�v�k�����ش.����QS�2�׈�b��Ǽ��&����P@y~��'���*5[�yhL��]��`i��e3����s6�TGgoU���߮�'F�J1��w�{bq6��j//�("�dW.������WV2~ h+@e�H)��^�	��tlH���&�������eU�+�!��·rpWt��0AM@�C%G��u��s�5J,��e�|˸B�Qȣ��[��j�h�%��>��5!���}���wֲ��7`�>HFdnp߅�+�<2�4�]�,��l|��j�L�(n7��G��ik�	îT ��M��;���RV���dl��49����:^^�Ī�`5�J�D�����m����a�.���ZZ�E|��b:����g���wc,��)���ͨ��x�NraԹ:�4�0� Vꅝ�f�C:}��Gd�1�!-])��u���{��V9���[e��M�0P���5�Í�햹�b���j��:t[N����*��ʽ���^�]X�ʈ�_�$�EԅW�����j��d�L�w#,~�_��t���^ 3qQ��5����F�=ֳhZ��Y�T=X�)/��2�l[�J���vn�ʓ��e��)�ko{����7�зq&ۚh�,�5?�
=��AmK�\���bP.ά�R��a�n�x��
��oݐj����yC`�Fj��y�Z�ĝs���j��ĥ.v?�9۾N�r+
��&4��8��\�mA������:��F�'�a"�q�F��q�E��vO�M�L�?�u�>��-|��1���/|.U�2=̍�����4U�R�\oև��i�c���ֽY�����dEA��ªk�~�9�]M�XFp"
��'7���/�E�-���p��{�ax������ۙurՑ{�TӖ�gh.�����r���#�_�|w�pP � �T��<3��_4l���V|�f�za`������C�.n��/��bw�֘՟c��
 �>:k��iW��9gA�(�"!���	s >�c�Ŋ1rs-��e��g���E�[��Ij�3�'5xYV���S�r%\�xcx��fb~C��%p��f$(I�P �U���n���'�����[�1A},��f���g�䚑��x�+¤���������1�l}lB��)B�A6����B���x'O�M�2�(qs�ԩ�:�¤��4��]B��e��8��,+�c����5F�cW^��N��5��1�/]ʤ��|Ub�P	��d0��m	�����_uT&7�f��vޙ�U�ӫ�*ħ�1��24,��TU�ρ+���]�+�޲=-o.��a3����D��L�_���#����P�i�$#��,i�����f��j�a���DW?&��QjU�������;HUH�ɧᰙk:ɫ�SZ�7��tm���9�mzV?�d~�O!�y (�>n)�nkӔ{
1���=!��:	���%���~Y�U>n▔��	/A���������sNP�&`�9�Cbn��`y�Q��Ŏ��J�V
������q�+"�D�j(Ot�4y�tPZ��������b����d�3g;:�J:O�6�nlo�����'�RB����j�\�5�{���`OL�?䥛f��*�~d�r�L�I��?��e�1o��洏��m(���C,��C,P�`r��,^?W�G�z�N:��U���[�nʔru#C1�6��� y�x������LŖ�T���� M�0k6t)��3���%Sh��r/��ϥ�~��pC$�'Q�<�X�5��t���%��nnw�L/\����4\�v�� �J���AM8:F��ȍ�m�Q��%�K)�N��n�5V&�_����YS�"�C��S��oȀvA.�-H�֙���M<��[�杓��0>��x���$$H\���sB�rnFM���N�׃{3�do��B$d3�v�I    �LE�Κwx}�K/Ы�c���M$���!�λ����F8q��6���Xr����8[Xj�nm��k�e��p�La�
��s��っ������m�4�������}[�8�&������ϿgC�#I�id�L9�I:�4�ư�Js�~�_�;>Gf5���[\��E�Ehx�m��Sw�X���}m�sE�#%��K������5_^����9��"G��C�q�Nǟ-�@N�`�˫��i�`���#�nu^�l�[ʕ���bQܞ��m���ܽ���b�0$�:WT��V��������=�����+j�.K����\��E�
�*B��\��Q���ر�ykY���W��72F��z�n1�y����X��_k����ǣ�o:��1,���p�?Gi��̼Y_��ap6M�0r��88|�.(BX������ss,L�EM赭ױ} \�j&��M�e|�ug����.�餥�bJ�	����jB�G�K��y�ro�:_�7�T��w��|Z�0G �g���[��%�a�ɞ��i�#!�w����,���GY�Ɩ�u<�aޙ���N�@C�o:�绲;�x�9��0��'�.a��L\�d�"IY�P1wH:t���:����찲�����`�wL
4��6�Ԁe�]��-,η�h���C~a�ԍ��^�^7K,j���6t��0�lSVo�Qٕ��ώY��S7+P���yI�� ��p1g�2�b���B�̙g����_�9 n� 9�J�Q=NNw�	���0;Փ=�.��Qb��`����-�h�Gw�lgW̥��M&��AC"�E��U�jbk�	'��s_��
0P/cp�W@Ž�Ђ~e�X�VaWȯ��٬?��[�R�͇2,-�ޥ��m"�G��0�d�j0yM�G@�୻��s<6�+.��S��MB��C�W�H����O�4}o��k��wvW��:|�"���q��łj5`��Iz�B�e��N����R*��p~Ƶ����W<Lcքr�YY�s�g�C�q��Ii������q�`1���C|z�X��"���(e�R(ƚp��Q_�o�G���i�� �%S�  -)� �<g��©Q��a�nK�N�)�q]�<����h��5n�%M����
:���='V��m�Ph��͔��e	��B�J�{�J�n�H��p�?�,�_.o�
ң,}zk��gV2��뢕A�^�F�p	�ե���%�Lg�����b�aF�՟��Y�Y<�%�r�j_��'T"�$�Y��_����%������s��(mA���ւۘ�͚^��Ѡ���
-�,IfR���E{�lfl��-7׍l��dc5@�
z�i
�И���+-���F���m?X<�X6K!�AZ=����e���~j�ʨ4����j�
Ɉ��v�K�r9�.=K	!�8.L�w�䝊���d�R�l��� ;%%�W�|B�ݦz]��p봞v���pi=�K|�,2�r*�mJ���"�:�@�����d|��J��n� Q����8?8=+�����b1�/��X/F
|���/��ǖH�� ~��5��s7��
�`��w�5��ص����K,�3q(��Zo�zJB�Ӈ�8:��f+���Go�x�"'b�5p삲�K��`�$�� >gwb��p���ӭs�/Ko��y����x��jV����d"���˞��-pm9�����?��רj@UM�-~���_O��_��!�ȷ١�=WV>���"�����
��j)�-ȹ@1�ބ ���Cc��X�̱A��9�2�P�Y�N��E��!	����b�,��(�ŵM�c���[D�Tnal���P;��]TZ�Q�u��ƆiG)v�+%���<�I&����ܽ��!���3W�d��_�(;������\%/�M�k�BKr�1
���K�lfԌ^ۛ�Fb�ul]V?�PDLr�To�ktڛJ9�M\�zqc{oQ�:T�LY�+� D[�0�����6��q�>������wTT��Z�����"[T[&RC��[A3���F�[�]
�Q��W|t�*:���%����3?���m�jpdB�:������,S]2xP�X��*N%�+;��1>J�@��݁��`��7��e�t�>}�6q���yx��=\2��t��|G����)\i0RK��*�m.��)��j{y�,U��'c�;��|���[�D!+`�Σ��LM��Ò{��c�0jvvU���}��y7�X�����O�����i�t�
zBBT�i�j-���zl�:>D��U2�~�Qq�����SӥLϠ�����M���Z�j߾��)�m���<����ex��
�������
�$eu'��2q��>.nr�S)��e�f�˽�IR��Eއ�Y�j�_j9��?�;f��Zo�p�9 3؎�nS�2�mj)�p�
VoP7�z�q�-nS <�wz����u�!�b:/d
>�q�|(�'���^��bM����e��2bHʇ8�U-#�Cٔ���{g����+�/�����K�r�Ӓ�ߊ��M[���n��rqR����K���P��}E���̷�ʾ�d��.�7�m�Y����=4
N	�V?DϿ�g��ߡ&|���e��,h�����Y�o�לU-h��������cP��t5���!���T�h[�*�u�+�� ����RD�~�&�Gr��W�2Lm�Gn���m��J~��#wC�D�$��A����'�xy���%��d�X7��pJ�>�^-�㸕��V(��	��A�m�V#y���f��
î�ٿ�/Ȃ sk�^p��螧JJ�C�HXI;y�5s�I�U�/�hkh�o��S��C�ɒ���+�~�+�V���D���6��zw��p�I�歔Ek�ݚJG�l�A���m�|�^l�>=���|Lf�a��ů�Pm��%'5/��'gA�x�/FcsF�zR��<k˜�ľ��K9�Y�j*�hኩ�A&�x��Y���rɍ�źU��GS_�p���������B1�14K���L-�+�I��;�Ė���hZ;��1V� y�Ж������w��D�u`��{�Q栨	������v����Sp��	��Q:bX@n�r#�5���_�{fUή\)�ֲ!�7��2X˴s�F���}�$Ya:�0-M���z��To��y�zO2u��F����I)N��,�*-K�����Z�b���$i�j�KT~� e2^T0Y�e�#�v�W~�JhYҵ�*�p��
�N�'�1�qxk��
��>�J|������t!�W���X��z�Ս�C�1Cj��y(Щ�z�1�䋔�Ԇ�p��)�:Q���K�
��I6�����4�R@G��-�����0-a����,�����"����%0q�Ѐ��ٸ�⹞�ǉx��yyc�G�W�M��7�#�{u#9�n��%abRB�?*>ײ�X�E]�᳴7:�"筢� �^��J����64�\����o@Y����9na���Uk���0�������/�J��yz�7��,� E�b�eõ> �;���WJ��r���.� 8�uj��������\���KWT] N�	�۬��R��1_Э7�#���q�;qD׭w�;T�I\!2�Ew*i��6f�����"p�.�/gR*�p�	-<�Z�G�x2���3$TQ�<��>�\��͍JpȲ��|2
���W<D5+,�n���oJ�|��twX�6-�`}���L�+>*�H�Β�������'
]Z���� \"�i�pK*�,�:~+G�Ҽ)rx2{��=����l�yUPL����52@PE���"I�_�a� ��f�x�
�~�JU����К�ga"�H)Ҿ�
p�ZE�Y�5���U�2ڨ��Wv��1�]��Jk攪�������Yh���-�0h����M0H(��8�
nN��ԑ[�d�TN�R�>��C.��1B���4�Vn��8o)֓�i&߻\Yu3ګ��`\���V|KkH�c���*�r��Db����>�����ߵWdn�b�R	�,���8K#�J�r}(R9֥�r `  _f]:�I��YzK�ލ6
�yB1$������}+�zr�����1��B�0 ���J ��+�b���/Ln�A.�9]���Qu�aʇ��'���� ����*�by�#��S��oف�sJ�)�������NlH.�°@Q����]�6��B��n�������X~N��� Z7匨p8'���rS��Jv�������2���RK�&R��*)� ���Ի
G��d39Տ�}m���mgr��x���6�`�xu����d`�
n�\�g�'�b#pϋauj�����l[����S_�~��8ȃ+��pq,��=�dJ��x?5�ɧxU.� �| =��lB�y�mc&�`i�v�{���<ʳ��y��\i�M�)KTb�W&ץ� ��&x�3���X�tt*-s�W�Ұ�6#\\��|��'fk�������t�p��?��f�O�}�Jb����M��U��o�&�o�躖��{��)���2�Z���ܮ����ZW
�
��dMV<)r��<��r�M䱁@�%�
f�o����u�5=��^X"_{ 2�g
)�yaB����<l#\Z�_�W������T���bҜ�(Ǥ���>�xՒ_�QH=c�9Iڏۗ��Jk���~��D��8�e3�����3�l!�:.����pC&�!���͎Ê���m\���ȏ�(����eK3�E�<Ҽ�up��~P,n�V�I�z!I"�>�++4e)�/�����~|��#qc����n\JJ"`�ۿ�2W,@ �]��n��b�E-Ic6Tßw���TiH�����{*g�T�+�����
v`mw��ex��ؗ,�򗬪�P4���C���K��^�l��ǯ?��?�� gO�     