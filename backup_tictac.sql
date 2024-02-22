PGDMP       &                |            backup_tictac    16.2    16.2 �               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16398    backup_tictac    DATABASE     �   CREATE DATABASE backup_tictac WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE backup_tictac;
                postgres    false            �            1259    16425    actividad_plan    TABLE     -  CREATE TABLE public.actividad_plan (
    id_actividad integer NOT NULL,
    id_plan integer NOT NULL,
    docente_apoyo character varying(100) NOT NULL,
    nombre text,
    fecha_inicio date,
    fecha_fin date,
    cumplimiento character varying(15),
    observaciones text,
    descripcion text
);
 "   DROP TABLE public.actividad_plan;
       public         heap    postgres    false            �            1259    16424    actividad_plan_id_actividad_seq    SEQUENCE     �   CREATE SEQUENCE public.actividad_plan_id_actividad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.actividad_plan_id_actividad_seq;
       public          postgres    false    222                       0    0    actividad_plan_id_actividad_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.actividad_plan_id_actividad_seq OWNED BY public.actividad_plan.id_actividad;
          public          postgres    false    221            �            1259    16471    actividad_proyecto    TABLE     �   CREATE TABLE public.actividad_proyecto (
    id_actividad integer NOT NULL,
    nombre text,
    descripcion text,
    observaciones text,
    cumplimiento character varying(15)
);
 &   DROP TABLE public.actividad_proyecto;
       public         heap    postgres    false            �            1259    16470 #   actividad_proyecto_id_actividad_seq    SEQUENCE     �   CREATE SEQUENCE public.actividad_proyecto_id_actividad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.actividad_proyecto_id_actividad_seq;
       public          postgres    false    233                       0    0 #   actividad_proyecto_id_actividad_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.actividad_proyecto_id_actividad_seq OWNED BY public.actividad_proyecto.id_actividad;
          public          postgres    false    232            �            1259    16400    ciudad    TABLE     b   CREATE TABLE public.ciudad (
    id_ciudad integer NOT NULL,
    nombre character varying(100)
);
    DROP TABLE public.ciudad;
       public         heap    postgres    false            �            1259    16399    ciudad_id_ciudad_seq    SEQUENCE     �   CREATE SEQUENCE public.ciudad_id_ciudad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.ciudad_id_ciudad_seq;
       public          postgres    false    216                       0    0    ciudad_id_ciudad_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.ciudad_id_ciudad_seq OWNED BY public.ciudad.id_ciudad;
          public          postgres    false    215            �            1259    16501    competencia    TABLE     p   CREATE TABLE public.competencia (
    id_competencia integer NOT NULL,
    nombre text,
    id_linea integer
);
    DROP TABLE public.competencia;
       public         heap    postgres    false            �            1259    16500    competencia_id_competencia_seq    SEQUENCE     �   CREATE SEQUENCE public.competencia_id_competencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.competencia_id_competencia_seq;
       public          postgres    false    240                       0    0    competencia_id_competencia_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.competencia_id_competencia_seq OWNED BY public.competencia.id_competencia;
          public          postgres    false    239            �            1259    16485    contenido_digital    TABLE     }  CREATE TABLE public.contenido_digital (
    id_contenido_digital integer NOT NULL,
    docente_autor character varying(100),
    nombre_cont_digital text,
    visibilidad smallint,
    id_linea integer NOT NULL,
    estado character varying(10),
    recomendacion text,
    fecha_aprobacion date,
    fecha_creacion date,
    recurso text,
    descripcion text,
    uso integer
);
 %   DROP TABLE public.contenido_digital;
       public         heap    postgres    false            �            1259    16484 *   contenido_digital_id_contenido_digital_seq    SEQUENCE     �   CREATE SEQUENCE public.contenido_digital_id_contenido_digital_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 A   DROP SEQUENCE public.contenido_digital_id_contenido_digital_seq;
       public          postgres    false    236                       0    0 *   contenido_digital_id_contenido_digital_seq    SEQUENCE OWNED BY     y   ALTER SEQUENCE public.contenido_digital_id_contenido_digital_seq OWNED BY public.contenido_digital.id_contenido_digital;
          public          postgres    false    235            �            1259    16457    curso    TABLE     u   CREATE TABLE public.curso (
    grado integer NOT NULL,
    jornada character varying(2),
    id integer NOT NULL
);
    DROP TABLE public.curso;
       public         heap    postgres    false            �            1259    16456    curso_id_seq    SEQUENCE     �   CREATE SEQUENCE public.curso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.curso_id_seq;
       public          postgres    false    229                        0    0    curso_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.curso_id_seq OWNED BY public.curso.id;
          public          postgres    false    228            �            1259    16464    curso_proyecto    TABLE     �   CREATE TABLE public.curso_proyecto (
    id integer NOT NULL,
    id_proyecto integer NOT NULL,
    id_actividad integer NOT NULL
);
 "   DROP TABLE public.curso_proyecto;
       public         heap    postgres    false            �            1259    16463    curso_proyecto_id_seq    SEQUENCE     �   CREATE SEQUENCE public.curso_proyecto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.curso_proyecto_id_seq;
       public          postgres    false    231            !           0    0    curso_proyecto_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.curso_proyecto_id_seq OWNED BY public.curso_proyecto.id;
          public          postgres    false    230            �            1259    16479    docente    TABLE     �  CREATE TABLE public.docente (
    id_docente character varying(50) NOT NULL,
    numero_proyectos_sociales integer,
    numero_proyectos_sexualidad integer,
    numero_proyectos_ambiental integer,
    numero_proyectos_emprendimiento integer,
    numero_proyectos_tic integer,
    numero_contenidos_sociales integer,
    numero_contenidos_sexualidad integer,
    numero_contenidos_ambiental integer,
    numero_contenidos_emprendimiento integer,
    numero_contenidos_tic integer,
    numero_herramientas_sociales integer,
    numero_herramientas_sexualidad integer,
    numero_herramientas_ambiental integer,
    numero_herramientas_emprendimiento integer,
    numero_herramientas_tic integer
);
    DROP TABLE public.docente;
       public         heap    postgres    false            �            1259    16567    docente_plantrabajo    TABLE     �   CREATE TABLE public.docente_plantrabajo (
    id integer NOT NULL,
    id_actividad_plan integer NOT NULL,
    id_docente character varying(10) NOT NULL
);
 '   DROP TABLE public.docente_plantrabajo;
       public         heap    postgres    false            �            1259    16566    docente_plantrabajo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.docente_plantrabajo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.docente_plantrabajo_id_seq;
       public          postgres    false    255            "           0    0    docente_plantrabajo_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.docente_plantrabajo_id_seq OWNED BY public.docente_plantrabajo.id;
          public          postgres    false    254                        1259    16573 
   estudiante    TABLE     �   CREATE TABLE public.estudiante (
    id_estudiante character varying(50) NOT NULL,
    nombre character varying(300),
    grado integer
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false                       1259    16579    estudiante_proyecto    TABLE     �   CREATE TABLE public.estudiante_proyecto (
    id integer NOT NULL,
    id_estudiante character varying(50) NOT NULL,
    id_actividad integer
);
 '   DROP TABLE public.estudiante_proyecto;
       public         heap    postgres    false                       1259    16578    estudiante_proyecto_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_proyecto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.estudiante_proyecto_id_seq;
       public          postgres    false    258            #           0    0    estudiante_proyecto_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.estudiante_proyecto_id_seq OWNED BY public.estudiante_proyecto.id;
          public          postgres    false    257                       1259    16586    evidencia_experiencia    TABLE     u   CREATE TABLE public.evidencia_experiencia (
    id integer NOT NULL,
    id_experiencia integer,
    recurso text
);
 )   DROP TABLE public.evidencia_experiencia;
       public         heap    postgres    false                       1259    16585    evidencia_experiencia_id_seq    SEQUENCE     �   CREATE SEQUENCE public.evidencia_experiencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.evidencia_experiencia_id_seq;
       public          postgres    false    260            $           0    0    evidencia_experiencia_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.evidencia_experiencia_id_seq OWNED BY public.evidencia_experiencia.id;
          public          postgres    false    259                       1259    16595    evidencia_plan_trabajo    TABLE     o   CREATE TABLE public.evidencia_plan_trabajo (
    id integer NOT NULL,
    id_plan integer,
    recurso text
);
 *   DROP TABLE public.evidencia_plan_trabajo;
       public         heap    postgres    false                       1259    16594    evidencia_plan_trabajo_id_seq    SEQUENCE     �   CREATE SEQUENCE public.evidencia_plan_trabajo_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.evidencia_plan_trabajo_id_seq;
       public          postgres    false    262            %           0    0    evidencia_plan_trabajo_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.evidencia_plan_trabajo_id_seq OWNED BY public.evidencia_plan_trabajo.id;
          public          postgres    false    261                       1259    16604    evidencia_proyecto_aula    TABLE     t   CREATE TABLE public.evidencia_proyecto_aula (
    id integer NOT NULL,
    id_proyecto integer,
    recurso text
);
 +   DROP TABLE public.evidencia_proyecto_aula;
       public         heap    postgres    false                       1259    16603    evidencia_proyecto_aula_id_seq    SEQUENCE     �   CREATE SEQUENCE public.evidencia_proyecto_aula_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.evidencia_proyecto_aula_id_seq;
       public          postgres    false    264            &           0    0    evidencia_proyecto_aula_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.evidencia_proyecto_aula_id_seq OWNED BY public.evidencia_proyecto_aula.id;
          public          postgres    false    263            
           1259    16613    experiencia    TABLE     �   CREATE TABLE public.experiencia (
    id integer NOT NULL,
    nombre text,
    descripcion text,
    id_linea integer,
    fecha date
);
    DROP TABLE public.experiencia;
       public         heap    postgres    false            	           1259    16612    experiencia_id_seq    SEQUENCE     �   CREATE SEQUENCE public.experiencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.experiencia_id_seq;
       public          postgres    false    266            '           0    0    experiencia_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.experiencia_id_seq OWNED BY public.experiencia.id;
          public          postgres    false    265            �            1259    16558    herramienta    TABLE     z  CREATE TABLE public.herramienta (
    id_herramienta integer NOT NULL,
    id_tema integer,
    docente_autor character varying(100),
    nombre_herramienta text,
    objetivos text,
    visibilidad smallint,
    comentarios text,
    estado character varying(10),
    recomendacion text,
    fecha_aprobacion date,
    fecha_creacion date,
    recurso text,
    uso integer
);
    DROP TABLE public.herramienta;
       public         heap    postgres    false            �            1259    16557    herramienta_id_herramienta_seq    SEQUENCE     �   CREATE SEQUENCE public.herramienta_id_herramienta_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.herramienta_id_herramienta_seq;
       public          postgres    false    253            (           0    0    herramienta_id_herramienta_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.herramienta_id_herramienta_seq OWNED BY public.herramienta.id_herramienta;
          public          postgres    false    252                       1259    16622    informe    TABLE     �   CREATE TABLE public.informe (
    id integer NOT NULL,
    nombre text,
    fecha date,
    recurso text,
    docente_autor character varying(15)
);
    DROP TABLE public.informe;
       public         heap    postgres    false                       1259    16621    informe_id_seq    SEQUENCE     �   CREATE SEQUENCE public.informe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.informe_id_seq;
       public          postgres    false    268            )           0    0    informe_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.informe_id_seq OWNED BY public.informe.id;
          public          postgres    false    267            �            1259    16407    institucion    TABLE        CREATE TABLE public.institucion (
    id_institucion integer NOT NULL,
    nombre text NOT NULL,
    id_ciudad integer NOT NULL,
    numero_proyectos_sociales integer,
    numero_proyectos_sexualidad integer,
    numero_proyectos_ambiental integer,
    numero_proyectos_emprendimiento integer,
    numero_proyectos_tic integer,
    numero_herramientas_sociales integer,
    numero_herramientas_sexualidad integer,
    numero_herramientas_ambiental integer,
    numero_herramientas_emprendimiento integer,
    numero_herramientas_tic integer
);
    DROP TABLE public.institucion;
       public         heap    postgres    false            �            1259    16406    institucion_id_institucion_seq    SEQUENCE     �   CREATE SEQUENCE public.institucion_id_institucion_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.institucion_id_institucion_seq;
       public          postgres    false    218            *           0    0    institucion_id_institucion_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.institucion_id_institucion_seq OWNED BY public.institucion.id_institucion;
          public          postgres    false    217            �            1259    16509    lider_linea    TABLE     �   CREATE TABLE public.lider_linea (
    id_docente character varying(100) NOT NULL,
    id_linea integer NOT NULL,
    fecha_inicio date,
    eslider boolean
);
    DROP TABLE public.lider_linea;
       public         heap    postgres    false            �            1259    16494    linea_transversal    TABLE     l   CREATE TABLE public.linea_transversal (
    id_linea integer NOT NULL,
    nombre character varying(100)
);
 %   DROP TABLE public.linea_transversal;
       public         heap    postgres    false            �            1259    16493    linea_transversal_id_linea_seq    SEQUENCE     �   CREATE SEQUENCE public.linea_transversal_id_linea_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.linea_transversal_id_linea_seq;
       public          postgres    false    238            +           0    0    linea_transversal_id_linea_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.linea_transversal_id_linea_seq OWNED BY public.linea_transversal.id_linea;
          public          postgres    false    237            �            1259    16531    momento    TABLE     �   CREATE TABLE public.momento (
    id_momento integer NOT NULL,
    id_herramienta integer NOT NULL,
    nombre text,
    descripcion text
);
    DROP TABLE public.momento;
       public         heap    postgres    false            �            1259    16530    momento_id_momento_seq    SEQUENCE     �   CREATE SEQUENCE public.momento_id_momento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.momento_id_momento_seq;
       public          postgres    false    247            ,           0    0    momento_id_momento_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.momento_id_momento_seq OWNED BY public.momento.id_momento;
          public          postgres    false    246            �            1259    16440    persona    TABLE     7  CREATE TABLE public.persona (
    cedula character varying(10) NOT NULL,
    nombre character varying(200),
    apellido character varying(200),
    password character varying(200),
    fecha_nacimiento date,
    codigo character varying(30),
    id_rol integer NOT NULL,
    id_institucion integer NOT NULL
);
    DROP TABLE public.persona;
       public         heap    postgres    false            �            1259    16416    plan_trabajo    TABLE     �   CREATE TABLE public.plan_trabajo (
    id_plan integer NOT NULL,
    id_linea integer NOT NULL,
    nombre text,
    lecciones_aprendidas text,
    cierre boolean,
    ano character varying(5)
);
     DROP TABLE public.plan_trabajo;
       public         heap    postgres    false            �            1259    16415    plan_trabajo_id_plan_seq    SEQUENCE     �   CREATE SEQUENCE public.plan_trabajo_id_plan_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.plan_trabajo_id_plan_seq;
       public          postgres    false    220            -           0    0    plan_trabajo_id_plan_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.plan_trabajo_id_plan_seq OWNED BY public.plan_trabajo.id_plan;
          public          postgres    false    219                       1259    16630 	   poblacion    TABLE     V   CREATE TABLE public.poblacion (
    id_poblacion integer NOT NULL,
    nombre text
);
    DROP TABLE public.poblacion;
       public         heap    postgres    false                       1259    16638    poblacion_contenido_digital    TABLE     �   CREATE TABLE public.poblacion_contenido_digital (
    id integer NOT NULL,
    id_contenido_digital integer NOT NULL,
    id_poblacion integer NOT NULL
);
 /   DROP TABLE public.poblacion_contenido_digital;
       public         heap    postgres    false                       1259    16637 "   poblacion_contenido_digital_id_seq    SEQUENCE     �   CREATE SEQUENCE public.poblacion_contenido_digital_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 9   DROP SEQUENCE public.poblacion_contenido_digital_id_seq;
       public          postgres    false    271            .           0    0 "   poblacion_contenido_digital_id_seq    SEQUENCE OWNED BY     i   ALTER SEQUENCE public.poblacion_contenido_digital_id_seq OWNED BY public.poblacion_contenido_digital.id;
          public          postgres    false    270                       1259    16645    poblacion_herramienta    TABLE     �   CREATE TABLE public.poblacion_herramienta (
    id integer NOT NULL,
    id_herramienta integer NOT NULL,
    id_poblacion integer NOT NULL
);
 )   DROP TABLE public.poblacion_herramienta;
       public         heap    postgres    false                       1259    16644    poblacion_herramienta_id_seq    SEQUENCE     �   CREATE SEQUENCE public.poblacion_herramienta_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.poblacion_herramienta_id_seq;
       public          postgres    false    273            /           0    0    poblacion_herramienta_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.poblacion_herramienta_id_seq OWNED BY public.poblacion_herramienta.id;
          public          postgres    false    272            �            1259    16540    proceso    TABLE     �   CREATE TABLE public.proceso (
    id_proceso integer NOT NULL,
    id_momento integer,
    descripcion text,
    tiempo integer
);
    DROP TABLE public.proceso;
       public         heap    postgres    false            �            1259    16539    proceso_id_proceso_seq    SEQUENCE     �   CREATE SEQUENCE public.proceso_id_proceso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.proceso_id_proceso_seq;
       public          postgres    false    249            0           0    0    proceso_id_proceso_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.proceso_id_proceso_seq OWNED BY public.proceso.id_proceso;
          public          postgres    false    248            �            1259    16549    proyecto_aula    TABLE       CREATE TABLE public.proyecto_aula (
    id_proyecto integer NOT NULL,
    grado integer,
    id_tema integer,
    docente_lider character varying(100),
    fecha_inicio date,
    fecha_fin date,
    nombre text,
    visibilidad smallint,
    estado character varying(10)
);
 !   DROP TABLE public.proyecto_aula;
       public         heap    postgres    false            �            1259    16548    proyecto_aula_id_proyecto_seq    SEQUENCE     �   CREATE SEQUENCE public.proyecto_aula_id_proyecto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.proyecto_aula_id_proyecto_seq;
       public          postgres    false    251            1           0    0    proyecto_aula_id_proyecto_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.proyecto_aula_id_proyecto_seq OWNED BY public.proyecto_aula.id_proyecto;
          public          postgres    false    250            �            1259    16522    recurso    TABLE     s   CREATE TABLE public.recurso (
    id_recurso integer NOT NULL,
    tipo character varying(100),
    nombre text
);
    DROP TABLE public.recurso;
       public         heap    postgres    false            �            1259    16521    recurso_id_recurso_seq    SEQUENCE     �   CREATE SEQUENCE public.recurso_id_recurso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.recurso_id_recurso_seq;
       public          postgres    false    245            2           0    0    recurso_id_recurso_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.recurso_id_recurso_seq OWNED BY public.recurso.id_recurso;
          public          postgres    false    244            �            1259    16515    recurso_proceso    TABLE     �   CREATE TABLE public.recurso_proceso (
    id integer NOT NULL,
    id_proceso integer NOT NULL,
    id_recurso integer NOT NULL
);
 #   DROP TABLE public.recurso_proceso;
       public         heap    postgres    false            �            1259    16514    recurso_proceso_id_seq    SEQUENCE     �   CREATE SEQUENCE public.recurso_proceso_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.recurso_proceso_id_seq;
       public          postgres    false    243            3           0    0    recurso_proceso_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.recurso_proceso_id_seq OWNED BY public.recurso_proceso.id;
          public          postgres    false    242            �            1259    16434    rol    TABLE     [   CREATE TABLE public.rol (
    id_rol integer NOT NULL,
    nombre character varying(50)
);
    DROP TABLE public.rol;
       public         heap    postgres    false            �            1259    16433    rol_id_rol_seq    SEQUENCE     �   CREATE SEQUENCE public.rol_id_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.rol_id_rol_seq;
       public          postgres    false    224            4           0    0    rol_id_rol_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.rol_id_rol_seq OWNED BY public.rol.id_rol;
          public          postgres    false    223                       1259    16652 	   situacion    TABLE     �   CREATE TABLE public.situacion (
    id integer NOT NULL,
    descripcion text,
    fecha character varying(8),
    casos integer,
    linea integer NOT NULL,
    titulo text,
    id_plan integer NOT NULL,
    municipio integer NOT NULL
);
    DROP TABLE public.situacion;
       public         heap    postgres    false                       1259    16651    situacion_id_seq    SEQUENCE     �   CREATE SEQUENCE public.situacion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.situacion_id_seq;
       public          postgres    false    275            5           0    0    situacion_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.situacion_id_seq OWNED BY public.situacion.id;
          public          postgres    false    274            �            1259    16448    tema    TABLE     �   CREATE TABLE public.tema (
    id_tema integer NOT NULL,
    nombre text,
    id_linea integer NOT NULL,
    id_competencia integer NOT NULL
);
    DROP TABLE public.tema;
       public         heap    postgres    false            �            1259    16447    tema_id_tema_seq    SEQUENCE     �   CREATE SEQUENCE public.tema_id_tema_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.tema_id_tema_seq;
       public          postgres    false    227            6           0    0    tema_id_tema_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.tema_id_tema_seq OWNED BY public.tema.id_tema;
          public          postgres    false    226            �           2604    16428    actividad_plan id_actividad    DEFAULT     �   ALTER TABLE ONLY public.actividad_plan ALTER COLUMN id_actividad SET DEFAULT nextval('public.actividad_plan_id_actividad_seq'::regclass);
 J   ALTER TABLE public.actividad_plan ALTER COLUMN id_actividad DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    16474    actividad_proyecto id_actividad    DEFAULT     �   ALTER TABLE ONLY public.actividad_proyecto ALTER COLUMN id_actividad SET DEFAULT nextval('public.actividad_proyecto_id_actividad_seq'::regclass);
 N   ALTER TABLE public.actividad_proyecto ALTER COLUMN id_actividad DROP DEFAULT;
       public          postgres    false    232    233    233            �           2604    16403    ciudad id_ciudad    DEFAULT     t   ALTER TABLE ONLY public.ciudad ALTER COLUMN id_ciudad SET DEFAULT nextval('public.ciudad_id_ciudad_seq'::regclass);
 ?   ALTER TABLE public.ciudad ALTER COLUMN id_ciudad DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    16504    competencia id_competencia    DEFAULT     �   ALTER TABLE ONLY public.competencia ALTER COLUMN id_competencia SET DEFAULT nextval('public.competencia_id_competencia_seq'::regclass);
 I   ALTER TABLE public.competencia ALTER COLUMN id_competencia DROP DEFAULT;
       public          postgres    false    240    239    240            �           2604    16488 &   contenido_digital id_contenido_digital    DEFAULT     �   ALTER TABLE ONLY public.contenido_digital ALTER COLUMN id_contenido_digital SET DEFAULT nextval('public.contenido_digital_id_contenido_digital_seq'::regclass);
 U   ALTER TABLE public.contenido_digital ALTER COLUMN id_contenido_digital DROP DEFAULT;
       public          postgres    false    236    235    236            �           2604    16460    curso id    DEFAULT     d   ALTER TABLE ONLY public.curso ALTER COLUMN id SET DEFAULT nextval('public.curso_id_seq'::regclass);
 7   ALTER TABLE public.curso ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    229    229            �           2604    16467    curso_proyecto id    DEFAULT     v   ALTER TABLE ONLY public.curso_proyecto ALTER COLUMN id SET DEFAULT nextval('public.curso_proyecto_id_seq'::regclass);
 @   ALTER TABLE public.curso_proyecto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    230    231    231            �           2604    16570    docente_plantrabajo id    DEFAULT     �   ALTER TABLE ONLY public.docente_plantrabajo ALTER COLUMN id SET DEFAULT nextval('public.docente_plantrabajo_id_seq'::regclass);
 E   ALTER TABLE public.docente_plantrabajo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    255    254    255            �           2604    16582    estudiante_proyecto id    DEFAULT     �   ALTER TABLE ONLY public.estudiante_proyecto ALTER COLUMN id SET DEFAULT nextval('public.estudiante_proyecto_id_seq'::regclass);
 E   ALTER TABLE public.estudiante_proyecto ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    258    257    258            �           2604    16589    evidencia_experiencia id    DEFAULT     �   ALTER TABLE ONLY public.evidencia_experiencia ALTER COLUMN id SET DEFAULT nextval('public.evidencia_experiencia_id_seq'::regclass);
 G   ALTER TABLE public.evidencia_experiencia ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    259    260    260                        2604    16598    evidencia_plan_trabajo id    DEFAULT     �   ALTER TABLE ONLY public.evidencia_plan_trabajo ALTER COLUMN id SET DEFAULT nextval('public.evidencia_plan_trabajo_id_seq'::regclass);
 H   ALTER TABLE public.evidencia_plan_trabajo ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    261    262    262                       2604    16607    evidencia_proyecto_aula id    DEFAULT     �   ALTER TABLE ONLY public.evidencia_proyecto_aula ALTER COLUMN id SET DEFAULT nextval('public.evidencia_proyecto_aula_id_seq'::regclass);
 I   ALTER TABLE public.evidencia_proyecto_aula ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    264    263    264                       2604    16616    experiencia id    DEFAULT     p   ALTER TABLE ONLY public.experiencia ALTER COLUMN id SET DEFAULT nextval('public.experiencia_id_seq'::regclass);
 =   ALTER TABLE public.experiencia ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    266    265    266            �           2604    16561    herramienta id_herramienta    DEFAULT     �   ALTER TABLE ONLY public.herramienta ALTER COLUMN id_herramienta SET DEFAULT nextval('public.herramienta_id_herramienta_seq'::regclass);
 I   ALTER TABLE public.herramienta ALTER COLUMN id_herramienta DROP DEFAULT;
       public          postgres    false    252    253    253                       2604    16625 
   informe id    DEFAULT     h   ALTER TABLE ONLY public.informe ALTER COLUMN id SET DEFAULT nextval('public.informe_id_seq'::regclass);
 9   ALTER TABLE public.informe ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    268    267    268            �           2604    16410    institucion id_institucion    DEFAULT     �   ALTER TABLE ONLY public.institucion ALTER COLUMN id_institucion SET DEFAULT nextval('public.institucion_id_institucion_seq'::regclass);
 I   ALTER TABLE public.institucion ALTER COLUMN id_institucion DROP DEFAULT;
       public          postgres    false    218    217    218            �           2604    16497    linea_transversal id_linea    DEFAULT     �   ALTER TABLE ONLY public.linea_transversal ALTER COLUMN id_linea SET DEFAULT nextval('public.linea_transversal_id_linea_seq'::regclass);
 I   ALTER TABLE public.linea_transversal ALTER COLUMN id_linea DROP DEFAULT;
       public          postgres    false    237    238    238            �           2604    16534    momento id_momento    DEFAULT     x   ALTER TABLE ONLY public.momento ALTER COLUMN id_momento SET DEFAULT nextval('public.momento_id_momento_seq'::regclass);
 A   ALTER TABLE public.momento ALTER COLUMN id_momento DROP DEFAULT;
       public          postgres    false    246    247    247            �           2604    16419    plan_trabajo id_plan    DEFAULT     |   ALTER TABLE ONLY public.plan_trabajo ALTER COLUMN id_plan SET DEFAULT nextval('public.plan_trabajo_id_plan_seq'::regclass);
 C   ALTER TABLE public.plan_trabajo ALTER COLUMN id_plan DROP DEFAULT;
       public          postgres    false    219    220    220                       2604    16641    poblacion_contenido_digital id    DEFAULT     �   ALTER TABLE ONLY public.poblacion_contenido_digital ALTER COLUMN id SET DEFAULT nextval('public.poblacion_contenido_digital_id_seq'::regclass);
 M   ALTER TABLE public.poblacion_contenido_digital ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    271    270    271                       2604    16648    poblacion_herramienta id    DEFAULT     �   ALTER TABLE ONLY public.poblacion_herramienta ALTER COLUMN id SET DEFAULT nextval('public.poblacion_herramienta_id_seq'::regclass);
 G   ALTER TABLE public.poblacion_herramienta ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    273    272    273            �           2604    16543    proceso id_proceso    DEFAULT     x   ALTER TABLE ONLY public.proceso ALTER COLUMN id_proceso SET DEFAULT nextval('public.proceso_id_proceso_seq'::regclass);
 A   ALTER TABLE public.proceso ALTER COLUMN id_proceso DROP DEFAULT;
       public          postgres    false    249    248    249            �           2604    16552    proyecto_aula id_proyecto    DEFAULT     �   ALTER TABLE ONLY public.proyecto_aula ALTER COLUMN id_proyecto SET DEFAULT nextval('public.proyecto_aula_id_proyecto_seq'::regclass);
 H   ALTER TABLE public.proyecto_aula ALTER COLUMN id_proyecto DROP DEFAULT;
       public          postgres    false    251    250    251            �           2604    16525    recurso id_recurso    DEFAULT     x   ALTER TABLE ONLY public.recurso ALTER COLUMN id_recurso SET DEFAULT nextval('public.recurso_id_recurso_seq'::regclass);
 A   ALTER TABLE public.recurso ALTER COLUMN id_recurso DROP DEFAULT;
       public          postgres    false    245    244    245            �           2604    16518    recurso_proceso id    DEFAULT     x   ALTER TABLE ONLY public.recurso_proceso ALTER COLUMN id SET DEFAULT nextval('public.recurso_proceso_id_seq'::regclass);
 A   ALTER TABLE public.recurso_proceso ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    243    242    243            �           2604    16437 
   rol id_rol    DEFAULT     h   ALTER TABLE ONLY public.rol ALTER COLUMN id_rol SET DEFAULT nextval('public.rol_id_rol_seq'::regclass);
 9   ALTER TABLE public.rol ALTER COLUMN id_rol DROP DEFAULT;
       public          postgres    false    223    224    224                       2604    16655    situacion id    DEFAULT     l   ALTER TABLE ONLY public.situacion ALTER COLUMN id SET DEFAULT nextval('public.situacion_id_seq'::regclass);
 ;   ALTER TABLE public.situacion ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    275    274    275            �           2604    16451    tema id_tema    DEFAULT     l   ALTER TABLE ONLY public.tema ALTER COLUMN id_tema SET DEFAULT nextval('public.tema_id_tema_seq'::regclass);
 ;   ALTER TABLE public.tema ALTER COLUMN id_tema DROP DEFAULT;
       public          postgres    false    226    227    227            �          0    16425    actividad_plan 
   TABLE DATA           �   COPY public.actividad_plan (id_actividad, id_plan, docente_apoyo, nombre, fecha_inicio, fecha_fin, cumplimiento, observaciones, descripcion) FROM stdin;
    public          postgres    false    222   �       �          0    16471    actividad_proyecto 
   TABLE DATA           l   COPY public.actividad_proyecto (id_actividad, nombre, descripcion, observaciones, cumplimiento) FROM stdin;
    public          postgres    false    233   �      �          0    16400    ciudad 
   TABLE DATA           3   COPY public.ciudad (id_ciudad, nombre) FROM stdin;
    public          postgres    false    216         �          0    16501    competencia 
   TABLE DATA           G   COPY public.competencia (id_competencia, nombre, id_linea) FROM stdin;
    public          postgres    false    240   �      �          0    16485    contenido_digital 
   TABLE DATA           �   COPY public.contenido_digital (id_contenido_digital, docente_autor, nombre_cont_digital, visibilidad, id_linea, estado, recomendacion, fecha_aprobacion, fecha_creacion, recurso, descripcion, uso) FROM stdin;
    public          postgres    false    236   :	      �          0    16457    curso 
   TABLE DATA           3   COPY public.curso (grado, jornada, id) FROM stdin;
    public          postgres    false    229   �      �          0    16464    curso_proyecto 
   TABLE DATA           G   COPY public.curso_proyecto (id, id_proyecto, id_actividad) FROM stdin;
    public          postgres    false    231   G      �          0    16479    docente 
   TABLE DATA           �  COPY public.docente (id_docente, numero_proyectos_sociales, numero_proyectos_sexualidad, numero_proyectos_ambiental, numero_proyectos_emprendimiento, numero_proyectos_tic, numero_contenidos_sociales, numero_contenidos_sexualidad, numero_contenidos_ambiental, numero_contenidos_emprendimiento, numero_contenidos_tic, numero_herramientas_sociales, numero_herramientas_sexualidad, numero_herramientas_ambiental, numero_herramientas_emprendimiento, numero_herramientas_tic) FROM stdin;
    public          postgres    false    234   �                 0    16567    docente_plantrabajo 
   TABLE DATA           P   COPY public.docente_plantrabajo (id, id_actividad_plan, id_docente) FROM stdin;
    public          postgres    false    255   '                0    16573 
   estudiante 
   TABLE DATA           B   COPY public.estudiante (id_estudiante, nombre, grado) FROM stdin;
    public          postgres    false    256   D                0    16579    estudiante_proyecto 
   TABLE DATA           N   COPY public.estudiante_proyecto (id, id_estudiante, id_actividad) FROM stdin;
    public          postgres    false    258                   0    16586    evidencia_experiencia 
   TABLE DATA           L   COPY public.evidencia_experiencia (id, id_experiencia, recurso) FROM stdin;
    public          postgres    false    260   D                0    16595    evidencia_plan_trabajo 
   TABLE DATA           F   COPY public.evidencia_plan_trabajo (id, id_plan, recurso) FROM stdin;
    public          postgres    false    262         	          0    16604    evidencia_proyecto_aula 
   TABLE DATA           K   COPY public.evidencia_proyecto_aula (id, id_proyecto, recurso) FROM stdin;
    public          postgres    false    264   ,                0    16613    experiencia 
   TABLE DATA           O   COPY public.experiencia (id, nombre, descripcion, id_linea, fecha) FROM stdin;
    public          postgres    false    266   I      �          0    16558    herramienta 
   TABLE DATA           �   COPY public.herramienta (id_herramienta, id_tema, docente_autor, nombre_herramienta, objetivos, visibilidad, comentarios, estado, recomendacion, fecha_aprobacion, fecha_creacion, recurso, uso) FROM stdin;
    public          postgres    false    253   �                0    16622    informe 
   TABLE DATA           L   COPY public.informe (id, nombre, fecha, recurso, docente_autor) FROM stdin;
    public          postgres    false    268         �          0    16407    institucion 
   TABLE DATA           m  COPY public.institucion (id_institucion, nombre, id_ciudad, numero_proyectos_sociales, numero_proyectos_sexualidad, numero_proyectos_ambiental, numero_proyectos_emprendimiento, numero_proyectos_tic, numero_herramientas_sociales, numero_herramientas_sexualidad, numero_herramientas_ambiental, numero_herramientas_emprendimiento, numero_herramientas_tic) FROM stdin;
    public          postgres    false    218   �      �          0    16509    lider_linea 
   TABLE DATA           R   COPY public.lider_linea (id_docente, id_linea, fecha_inicio, eslider) FROM stdin;
    public          postgres    false    241   m       �          0    16494    linea_transversal 
   TABLE DATA           =   COPY public.linea_transversal (id_linea, nombre) FROM stdin;
    public          postgres    false    238   �       �          0    16531    momento 
   TABLE DATA           R   COPY public.momento (id_momento, id_herramienta, nombre, descripcion) FROM stdin;
    public          postgres    false    247   3!      �          0    16440    persona 
   TABLE DATA           w   COPY public.persona (cedula, nombre, apellido, password, fecha_nacimiento, codigo, id_rol, id_institucion) FROM stdin;
    public          postgres    false    225   +,      �          0    16416    plan_trabajo 
   TABLE DATA           d   COPY public.plan_trabajo (id_plan, id_linea, nombre, lecciones_aprendidas, cierre, ano) FROM stdin;
    public          postgres    false    220   �-                0    16630 	   poblacion 
   TABLE DATA           9   COPY public.poblacion (id_poblacion, nombre) FROM stdin;
    public          postgres    false    269   �.                0    16638    poblacion_contenido_digital 
   TABLE DATA           ]   COPY public.poblacion_contenido_digital (id, id_contenido_digital, id_poblacion) FROM stdin;
    public          postgres    false    271   �.                0    16645    poblacion_herramienta 
   TABLE DATA           Q   COPY public.poblacion_herramienta (id, id_herramienta, id_poblacion) FROM stdin;
    public          postgres    false    273   D/      �          0    16540    proceso 
   TABLE DATA           N   COPY public.proceso (id_proceso, id_momento, descripcion, tiempo) FROM stdin;
    public          postgres    false    249   �/      �          0    16549    proyecto_aula 
   TABLE DATA           �   COPY public.proyecto_aula (id_proyecto, grado, id_tema, docente_lider, fecha_inicio, fecha_fin, nombre, visibilidad, estado) FROM stdin;
    public          postgres    false    251   1A      �          0    16522    recurso 
   TABLE DATA           ;   COPY public.recurso (id_recurso, tipo, nombre) FROM stdin;
    public          postgres    false    245   �B      �          0    16515    recurso_proceso 
   TABLE DATA           E   COPY public.recurso_proceso (id, id_proceso, id_recurso) FROM stdin;
    public          postgres    false    243   OF      �          0    16434    rol 
   TABLE DATA           -   COPY public.rol (id_rol, nombre) FROM stdin;
    public          postgres    false    224   vG                0    16652 	   situacion 
   TABLE DATA           e   COPY public.situacion (id, descripcion, fecha, casos, linea, titulo, id_plan, municipio) FROM stdin;
    public          postgres    false    275   �G      �          0    16448    tema 
   TABLE DATA           I   COPY public.tema (id_tema, nombre, id_linea, id_competencia) FROM stdin;
    public          postgres    false    227   AH      7           0    0    actividad_plan_id_actividad_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.actividad_plan_id_actividad_seq', 1, false);
          public          postgres    false    221            8           0    0 #   actividad_proyecto_id_actividad_seq    SEQUENCE SET     R   SELECT pg_catalog.setval('public.actividad_proyecto_id_actividad_seq', 1, false);
          public          postgres    false    232            9           0    0    ciudad_id_ciudad_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.ciudad_id_ciudad_seq', 1, false);
          public          postgres    false    215            :           0    0    competencia_id_competencia_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.competencia_id_competencia_seq', 1, false);
          public          postgres    false    239            ;           0    0 *   contenido_digital_id_contenido_digital_seq    SEQUENCE SET     Y   SELECT pg_catalog.setval('public.contenido_digital_id_contenido_digital_seq', 1, false);
          public          postgres    false    235            <           0    0    curso_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.curso_id_seq', 1, false);
          public          postgres    false    228            =           0    0    curso_proyecto_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.curso_proyecto_id_seq', 1, false);
          public          postgres    false    230            >           0    0    docente_plantrabajo_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.docente_plantrabajo_id_seq', 1, false);
          public          postgres    false    254            ?           0    0    estudiante_proyecto_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.estudiante_proyecto_id_seq', 2, true);
          public          postgres    false    257            @           0    0    evidencia_experiencia_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.evidencia_experiencia_id_seq', 1, false);
          public          postgres    false    259            A           0    0    evidencia_plan_trabajo_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.evidencia_plan_trabajo_id_seq', 1, false);
          public          postgres    false    261            B           0    0    evidencia_proyecto_aula_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.evidencia_proyecto_aula_id_seq', 1, false);
          public          postgres    false    263            C           0    0    experiencia_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.experiencia_id_seq', 1, false);
          public          postgres    false    265            D           0    0    herramienta_id_herramienta_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.herramienta_id_herramienta_seq', 1, false);
          public          postgres    false    252            E           0    0    informe_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.informe_id_seq', 1, false);
          public          postgres    false    267            F           0    0    institucion_id_institucion_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.institucion_id_institucion_seq', 1, false);
          public          postgres    false    217            G           0    0    linea_transversal_id_linea_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.linea_transversal_id_linea_seq', 1, false);
          public          postgres    false    237            H           0    0    momento_id_momento_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.momento_id_momento_seq', 1, false);
          public          postgres    false    246            I           0    0    plan_trabajo_id_plan_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.plan_trabajo_id_plan_seq', 1, false);
          public          postgres    false    219            J           0    0 "   poblacion_contenido_digital_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.poblacion_contenido_digital_id_seq', 1, false);
          public          postgres    false    270            K           0    0    poblacion_herramienta_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.poblacion_herramienta_id_seq', 1, false);
          public          postgres    false    272            L           0    0    proceso_id_proceso_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.proceso_id_proceso_seq', 1, false);
          public          postgres    false    248            M           0    0    proyecto_aula_id_proyecto_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.proyecto_aula_id_proyecto_seq', 1, false);
          public          postgres    false    250            N           0    0    recurso_id_recurso_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.recurso_id_recurso_seq', 1, false);
          public          postgres    false    244            O           0    0    recurso_proceso_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.recurso_proceso_id_seq', 1, false);
          public          postgres    false    242            P           0    0    rol_id_rol_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.rol_id_rol_seq', 1, false);
          public          postgres    false    223            Q           0    0    situacion_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.situacion_id_seq', 1, false);
          public          postgres    false    274            R           0    0    tema_id_tema_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.tema_id_tema_seq', 1, false);
          public          postgres    false    226                       2606    16432 "   actividad_plan actividad_plan_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.actividad_plan
    ADD CONSTRAINT actividad_plan_pkey PRIMARY KEY (id_actividad);
 L   ALTER TABLE ONLY public.actividad_plan DROP CONSTRAINT actividad_plan_pkey;
       public            postgres    false    222                       2606    16478 *   actividad_proyecto actividad_proyecto_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.actividad_proyecto
    ADD CONSTRAINT actividad_proyecto_pkey PRIMARY KEY (id_actividad);
 T   ALTER TABLE ONLY public.actividad_proyecto DROP CONSTRAINT actividad_proyecto_pkey;
       public            postgres    false    233                       2606    16405    ciudad ciudad_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.ciudad
    ADD CONSTRAINT ciudad_pkey PRIMARY KEY (id_ciudad);
 <   ALTER TABLE ONLY public.ciudad DROP CONSTRAINT ciudad_pkey;
       public            postgres    false    216            "           2606    16508    competencia competencia_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.competencia
    ADD CONSTRAINT competencia_pkey PRIMARY KEY (id_competencia);
 F   ALTER TABLE ONLY public.competencia DROP CONSTRAINT competencia_pkey;
       public            postgres    false    240                       2606    16492 (   contenido_digital contenido_digital_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.contenido_digital
    ADD CONSTRAINT contenido_digital_pkey PRIMARY KEY (id_contenido_digital);
 R   ALTER TABLE ONLY public.contenido_digital DROP CONSTRAINT contenido_digital_pkey;
       public            postgres    false    236                       2606    16462    curso curso_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.curso DROP CONSTRAINT curso_pkey;
       public            postgres    false    229                       2606    16469 "   curso_proyecto curso_proyecto_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.curso_proyecto
    ADD CONSTRAINT curso_proyecto_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.curso_proyecto DROP CONSTRAINT curso_proyecto_pkey;
       public            postgres    false    231                       2606    16483    docente docente_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.docente
    ADD CONSTRAINT docente_pkey PRIMARY KEY (id_docente);
 >   ALTER TABLE ONLY public.docente DROP CONSTRAINT docente_pkey;
       public            postgres    false    234            2           2606    16572 ,   docente_plantrabajo docente_plantrabajo_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.docente_plantrabajo
    ADD CONSTRAINT docente_plantrabajo_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.docente_plantrabajo DROP CONSTRAINT docente_plantrabajo_pkey;
       public            postgres    false    255            4           2606    16577    estudiante estudiante_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id_estudiante);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    256            6           2606    16584 ,   estudiante_proyecto estudiante_proyecto_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.estudiante_proyecto
    ADD CONSTRAINT estudiante_proyecto_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.estudiante_proyecto DROP CONSTRAINT estudiante_proyecto_pkey;
       public            postgres    false    258            8           2606    16593 0   evidencia_experiencia evidencia_experiencia_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.evidencia_experiencia
    ADD CONSTRAINT evidencia_experiencia_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.evidencia_experiencia DROP CONSTRAINT evidencia_experiencia_pkey;
       public            postgres    false    260            :           2606    16602 2   evidencia_plan_trabajo evidencia_plan_trabajo_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.evidencia_plan_trabajo
    ADD CONSTRAINT evidencia_plan_trabajo_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.evidencia_plan_trabajo DROP CONSTRAINT evidencia_plan_trabajo_pkey;
       public            postgres    false    262            <           2606    16611 4   evidencia_proyecto_aula evidencia_proyecto_aula_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.evidencia_proyecto_aula
    ADD CONSTRAINT evidencia_proyecto_aula_pkey PRIMARY KEY (id);
 ^   ALTER TABLE ONLY public.evidencia_proyecto_aula DROP CONSTRAINT evidencia_proyecto_aula_pkey;
       public            postgres    false    264            >           2606    16620    experiencia experiencia_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.experiencia
    ADD CONSTRAINT experiencia_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.experiencia DROP CONSTRAINT experiencia_pkey;
       public            postgres    false    266            0           2606    16565    herramienta herramienta_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.herramienta
    ADD CONSTRAINT herramienta_pkey PRIMARY KEY (id_herramienta);
 F   ALTER TABLE ONLY public.herramienta DROP CONSTRAINT herramienta_pkey;
       public            postgres    false    253            @           2606    16629    informe informe_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.informe
    ADD CONSTRAINT informe_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.informe DROP CONSTRAINT informe_pkey;
       public            postgres    false    268            
           2606    16414    institucion institucion_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.institucion
    ADD CONSTRAINT institucion_pkey PRIMARY KEY (id_institucion);
 F   ALTER TABLE ONLY public.institucion DROP CONSTRAINT institucion_pkey;
       public            postgres    false    218            $           2606    16513    lider_linea lider_linea_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.lider_linea
    ADD CONSTRAINT lider_linea_pkey PRIMARY KEY (id_docente);
 F   ALTER TABLE ONLY public.lider_linea DROP CONSTRAINT lider_linea_pkey;
       public            postgres    false    241                        2606    16499 (   linea_transversal linea_transversal_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.linea_transversal
    ADD CONSTRAINT linea_transversal_pkey PRIMARY KEY (id_linea);
 R   ALTER TABLE ONLY public.linea_transversal DROP CONSTRAINT linea_transversal_pkey;
       public            postgres    false    238            *           2606    16538    momento momento_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.momento
    ADD CONSTRAINT momento_pkey PRIMARY KEY (id_momento);
 >   ALTER TABLE ONLY public.momento DROP CONSTRAINT momento_pkey;
       public            postgres    false    247                       2606    16446    persona persona_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (cedula);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public            postgres    false    225                       2606    16423    plan_trabajo plan_trabajo_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.plan_trabajo
    ADD CONSTRAINT plan_trabajo_pkey PRIMARY KEY (id_plan);
 H   ALTER TABLE ONLY public.plan_trabajo DROP CONSTRAINT plan_trabajo_pkey;
       public            postgres    false    220            D           2606    16643 <   poblacion_contenido_digital poblacion_contenido_digital_pkey 
   CONSTRAINT     z   ALTER TABLE ONLY public.poblacion_contenido_digital
    ADD CONSTRAINT poblacion_contenido_digital_pkey PRIMARY KEY (id);
 f   ALTER TABLE ONLY public.poblacion_contenido_digital DROP CONSTRAINT poblacion_contenido_digital_pkey;
       public            postgres    false    271            F           2606    16650 0   poblacion_herramienta poblacion_herramienta_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.poblacion_herramienta
    ADD CONSTRAINT poblacion_herramienta_pkey PRIMARY KEY (id);
 Z   ALTER TABLE ONLY public.poblacion_herramienta DROP CONSTRAINT poblacion_herramienta_pkey;
       public            postgres    false    273            B           2606    16636    poblacion poblacion_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.poblacion
    ADD CONSTRAINT poblacion_pkey PRIMARY KEY (id_poblacion);
 B   ALTER TABLE ONLY public.poblacion DROP CONSTRAINT poblacion_pkey;
       public            postgres    false    269            ,           2606    16547    proceso proceso_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.proceso
    ADD CONSTRAINT proceso_pkey PRIMARY KEY (id_proceso);
 >   ALTER TABLE ONLY public.proceso DROP CONSTRAINT proceso_pkey;
       public            postgres    false    249            .           2606    16556     proyecto_aula proyecto_aula_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.proyecto_aula
    ADD CONSTRAINT proyecto_aula_pkey PRIMARY KEY (id_proyecto);
 J   ALTER TABLE ONLY public.proyecto_aula DROP CONSTRAINT proyecto_aula_pkey;
       public            postgres    false    251            (           2606    16529    recurso recurso_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.recurso
    ADD CONSTRAINT recurso_pkey PRIMARY KEY (id_recurso);
 >   ALTER TABLE ONLY public.recurso DROP CONSTRAINT recurso_pkey;
       public            postgres    false    245            &           2606    16520 $   recurso_proceso recurso_proceso_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.recurso_proceso
    ADD CONSTRAINT recurso_proceso_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.recurso_proceso DROP CONSTRAINT recurso_proceso_pkey;
       public            postgres    false    243                       2606    16439    rol rol_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);
 6   ALTER TABLE ONLY public.rol DROP CONSTRAINT rol_pkey;
       public            postgres    false    224            H           2606    16659    situacion situacion_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.situacion
    ADD CONSTRAINT situacion_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.situacion DROP CONSTRAINT situacion_pkey;
       public            postgres    false    275                       2606    16455    tema tema_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.tema
    ADD CONSTRAINT tema_pkey PRIMARY KEY (id_tema);
 8   ALTER TABLE ONLY public.tema DROP CONSTRAINT tema_pkey;
       public            postgres    false    227            �   '  x����N�0���S��g�i��	� ��\SR�6r����#�ň��b����g����%H���i���q��8N�;�-��a�;^[?��JA���X�R�U�����1�o���y�+��l�G��hc�u�R����|���  0BI����T���G�p3�lJm���+�ݪ$�G���k(P I���3`�2���RFs�G���x*й�w��۱ߦ�f�ʨ���@�$��|�0w�lMR��R�l��Zf`r�Ҍ0��C��xa�18>+����[n�ݛ��|~V�;��      �     x�}U��#7�G_Ald��>81��:�~N.i�-�${����7^p��̉��s5G�wg�Q$���������p�<���]�����������\�(�
>͎᪻�@�� {�rc��I���v\
%�\�B��)���t?�`z��!v(T�XR�e��f�`eg�r�t��~�LQ(����7�hy���X�;;TZ�]�<f7P�\�W,�0 WЌ8� �1偑h�NX�F<��o��K����5��=4�W��%�/��/J^�G'�%�f�W���(\���5 ��h��⥷���h��d��-`�D�%�>Z��.q=q�*/�ٽ�vS����F`�����u�<�[2:�N�g(~:&��M�� `v@�g�p���B�� =�lA���wН��{��ٌ��T��������'A6�e�a��]sތ�c�%߯�����A�$�SZ�
������._�X4�g���Be}�Hzm�o~%��f+���L��7�}Rۥ�m����3�_w��nl>�CK{O�fG=1�	��BC I�^�/Zu3�������ی�V4�����-��c�V��#8"��?�p�K�hs�Z�\��>��k{{��E�|&��u�Oկg΅[����4��X������U����Ew���2�������D��{%�'	CG�u	3�J�5x��j� �"�Yw1Q��lgj�FA��/\��t�s~�&z?r0ǝ^�	­�~z׊]�Y<�@�O�m��t���������Uw��F��0�JQ<����j��]is      �   ~  x�5Q�N1�����Kr��!Np�@4�lt)���.:>�>����ǰt�όg4ls�mF����b ����N�ʗR�	gp>J<�Cs�]p�݁���pGi��<w!��j��1���uO1��zM�v��N�NP��@3r;����:�y𨧰�ci=�1G�7*���^m(v�=�%|���j��k	Q=�r�^1���e�	lF_X��Rj}A��7_=w�����nsO%p����P�#3�-�µ�+mH��9lî~�Y@}���̒�?�C��+h�s�\�_	�DpXs���ኘ�~�5����<����H>j��)�8��\�����2d����X�v�K)i٥d7оѮ������1O�"|<A�����      �   �  x��T�r�0�ɯ�x2�l�Q+�;.Ӝ���`p�F�߸t�O��ei;�&3z�������r�֏d���ca�ф$f~}��$�)�6�y�,)�+3P�{�B�% �w��uv~3�l��)�^��ݎF����e)�>G)yz��u�K1Y6gCc |bb��_��b>LM��|��a�ԺHF�Z��	��D�<M(���LJ��)��wYG�<"r�wa�M��9(����8�U���_O^���`�)�:פ��">A�P���{H��zi,�drT�p��>i
�����&��X�3�9��i~���Z�f��T�:������k�F0;�a*j[��o�N����_���}F�XK��m��6���IN���K�/�8LZ3��Wh��e������{����M��)$��;F��ڔ��ʎ
0�eI?R&}=��t��y��0pu�V&t����S=f�1����mZ��T��]�k�J�C�p�3{P�3�F˘*1����/��%��z�T�� �9d(kB���榻C�C�5Ys�-�v��� Z!�3��U�z�i�/rkG�0�B�ú��[&�k�:"��Vo�/ά��t9��k����T�b���S��  [ҙ      �   �  x��XM�7=�~s0��V��@`L4㵁$vۧ v��8�&{�n�hn��������o{Y ��G�-i��Xg���@%QdիW��q4���t:J�w����'�J���Q�{BJ�J����Ps�!IM%�ϵ�����Ԇ� ��QmtA�~�)H�$��Q?��e4�'�`�4�}2\\\�k�6m��RW�ڔ˧��?���������,�%��%%u�W��e\5�YjK����'��*J�e�0N
�74$���{��rI(�P�O�ی�+.5�|ąZ�sk�p�cR&6�)�%�����-�r�+������A�K�Q��i�w�CY�;�P�{�B8;+]
���e��R��n��+`G��e�x{��m'�0]���B�R�i���P�ý�{۔æl����O�@@�S^�R?�>��Ϳ���i8.�wLo1�S���X+�N�K�љm/�U��"0#,�n�PԬ= l�Ҫ9�����ZL�`���4�'��H�K���ր�����.�IX�ypb �i��h�t �Cm4yu��H��Ŝ�`X���m�-Yrch%TD�4;�pe��w����rl3w����:�^v(;���^�'�5Q�TP
j�o'a4���v9�# _���q�6�iE�ȝ�:W:��4w<��Mb��!/JVu� F"�+p�q�Ow -i!�&1n;��6OPޥ+���<�� uGprc�-��&��0H�I<�=���f@�t��H�	O'���8�U.�*��Q���,��a<֫s�]-�Y��,��j~�֋���N�����pO]�r����T�����.d��cRA�����p�����T�������]*W����(g��0Ɇ{��B0�j�����Ij�K��^���i3%�� X}�ʈ�R���W((}2Pʢ�r-����3N��n^�*�'��w"1�/��+�=Ex���&g<�4�{�q��Σ��)Ĥ�w^����3�y�3�v��γ�[�re*ƫ.e�>�`h�*Z�+��ю��a��.\��r���Gņ�-j�(�����'Y��%RR�.�^����Z�4uT@�h�j�8�L�l'�
�#gN���(����z�F��$�����k2I�O^�>����Oo��t��{q|�_�+9�
@�I$W����ʑ}%l��kh�Q&��}��*�]�-�2�|��hyŎ��r�U�ǣd��.��ZX���q?�K
���Ӈ�Ҡs&����,���+ȼuy"�#��j�����+io��e�h���`���2�;���u��n��#|$X���'���選d׭I֏GnM�I4L�G�������)ά���ggc�K�-;;s߭�yٜ�u�+�w������m�;j�o�P)�;���a0���MP�Mɿn���.�^m^�+>o8m�~�3H}�]�YR�O����$�.���A=@�q�� VuF
0��[���,�t����8�������OX[:>ay��HG��|n���-�W!Jn|6]!�v}4�~�)�u��77x��� �nL+���
�EjTg'�Q��n7�9�v^�����ݢSw9�[�k��`؋:�:��X�E��'˓C��O�߫���,@�^��4v��w��b2]�u�\�Ꮲ�q������#�k�}�
3��  ;>}��v�#`�-%w�-�o���\g.]S�����څ�2;�t���:�1�Q���<<8C�#p�pN��I|����r��e�.=-;�w�8<<���ܴ��bɢ�n��\���u��:��$J�%�|�[I�;�:�0*�d��%8�`T�=�-T�=*KӞ�]W�l��a�nٶ3�p���3ٍ`�����}	���_@n!XP�t�o������6TY�uK{���y>���֦��sū��o���Oқ��<h���R��ba��0%jg{i���2��"�[[@�҈��k����(�6{      �   B   x���� ki�"�@��`�9"7��B�1����L|\�a&��>�Ģ�c�$�(�_������ݯP      �   7   x����0���=L%$d��?G{��p�/��-��ٔ���0�˘E9����Y�      �   �   x�}O˭ ;�a�8vy��Q�Ъ���RƱ���Dk钅�=qn8'�,����1ĈF�ۊ�I��r(�m��S{EIS�S�}~!� ��y����(X�
� k�[�Fw0��r���-����U������i��?�߃��.�*^             x������ � �         �   x�5�KN�@D�=�� ��tA�)�[6�gZjf��n����K���zU��l�܊�@��kz��|����{�E	gnG��R�I�r��0C7���״�e��d�+j4~!�h�:I��rR�}��8E-����1�.���[Z�H��IBw��	x�a,���5>E3�H�G�*�@OVB�UN����[XX��+��/��Jy            x�3�4�4��2�4Q1z\\\ b�         �   x��ϻ�0�����\4�.&͏m8(�)z���۷=��3�u!,�D�3�x�6�~F�)^v��Kv&�O��Ѣ��K&�%�D�-��"	f��`݌�6��fj�>�5v4�p�[�\��ais���G^�����`vP@�R���R�� � ���qf�W*��q��"��/�vlm            x������ � �      	      x������ � �         �  x�]�An�0E��)� �`;I���l�� ����Q�T��6>@W>�.�O�@��$q������~�e�L^�ivAro��r�Tt4!���%NcK���|�!��m�4��Y�\CybW�oh�=�^)i�و)j!)�c�IՄIi�q^�X�,��4�Q�!0�/�@��"ed�8���/��pxe3��M3=��h��F�X8r��X2�q����+CD��F�Rp���j�7u7�aw���l�w������+B��e�s3N-3��x>.�cp\��B|4�@�fw��e�ק��`6ZȾuA�2	���0��� >�XCl�Vդ^�����ڗOyzl��?ܮ�^�O
8)r ����i9��-g�@\�!/��>�n�r������I#w��������p�������J      �   %  x��XM��6=˿B���n���ܲ��E�b���h�v��"R���g�qsX䖫�X^�ԇ=3A6ЍVKT�U�ի*�Qe�y�]�W�׵��ձdq�T!ο������R��෌����������qDUk��K�^����hsEk�-Z.�]�Y%$֞�f�j���Fo�EG�;V��qt,�[^�j�j���i%���;�
�x�Jw�S%7l���{��{m��-.c�}Ϟyp��R��t��֬�>wq-Jq�/�"��jy�H�-�_�l͓y6M��,�/�y��4�}u[�B��i��˧�V�3;ދ��}[K���c�N�i�e���������i>�(�,w���!��o��KN�F8�G^T�qV��h9�G�h�di������Qqf��Fp =h�`v�YUC��n8�  ^Uk'tmE���l�E��.��i��]a���Kl���D�7vo�2�>����>Ţ5V�Gl�����pH�`u ���`�K��7� �E��̜?�����7��ӡ�^S��_���9��)���������zc�M�8��9\ ��.ރ$�=�k.�&�p��H�v�ZL��H�HV��揂�@��~�ow���m�,������W�"�#Ǟ�N8�9�a�)��(��9_Z���B=BNiuG���BKf�t��:�Zq��ALB�.H��ݣư{�@r��%8���kxu�Ј§��dɱ:�A�|џY�����݀q�O#}���h�q9fP^���sl�=�����ڞŎ��?�bf;�n�ɺ��n`�"�`�|����"D�G��VO^�V�E���d�,���*�@��;m�,�z���k.��~�!!�'��᷶��o��������O�պ�s?�
�d]�܆�g(�����3�����`�m%�ʈG��ŏW�`bU�+��L��q���n$��ּh��{����J���L;��N�V�
�܀�2�,{�4'̦(�:WK��>9dדe��@V�� �b�>�W��'���!��D��~�0��|����.��1�@�u�z����p�vEw�P���/�L�b�w�#Ib]z�}$�K�UiХx	pzU!��/�9�k��@�;�_G*݈#U�k�s7|�z��8S��K"%$�S Uct'QN
�U�ų�Ϥ�8�I효H*/���Cn�K+�:�@�4�&�p�L�[��*I/ȴ�T��y���M��@�Q�<-��*Z]qN�RX��4�T:��T���vm�;��\�0ޟ?R5{�� C�Lp�����5%?�����˩''d@��R&��;y��L��)��$/aPG��r �;&�gW����2.Md9be�<���;��&�$Y�"��M|VWE&M���G.y����=�:Z��CWB_���,(��j�=����-pj���Mg���i���4*\Ѳ��\�]y�Q�:O1lE:Ub���i��u����P��{�jJ/^��'sL:�N�&/��s�E%��R�tMG�3d��,��|:Oo�s�^^¹B�w�Ms�{���q�E6���ޣ�N6�&��t�^�Hj��>@���'��]s���v�"TW,H<ž��i���� U��r�X� E��o��k��g~}N�h�ؠ�>���y6F2W��	�>��~4��T��P���t���e�$�˦`�7��ݡt��'�h;�3eC��D���}�{�;U:�%��q��>A�KP��*�k�5
p�ύ������
C]7�( ~��@R(��E�_\��$��w�������K�?�C?
��=���WT�o�g��g�crM_�|�G���M(H�v:1�^�,�O��f�T~�qj��&�:��R����_]^[��z�p���-�t}�4>�veB�}�g��l���E����X�!�E��i���N���$�;O�-p�ĂC�Fw���;n\����L��Ёƿbd���w�h!|�$4�֊f#���ҕ�/����/��H��nb�׬�+n�C��j�Ir���~��M&�� Kr��         �   x��͹�   ��p�p��lt�4$�K.	������>����i*��s�a�́&�޼J4��Z�ϻ�xsj1�D�ƕ^I�zJ��;��Z�e������P��D.�r��)\�~ə�_n&:|���2g�}'l���= �^"K�      �   �  x�U��n�0�g�)��ZF��*	�.np��`W�S�l}��[W�X�vb�N����~�E3݈��d�M��\�EC���1���A��U��Ж+�-�~��upfR�����Hx
̆y������%XG0\f��iDP�h�D�h%��ս��A4I]D��y���BY']�K���e�i[����C�{O���L߁3\M�=Y���)kʉ��{� _d
��_x ��M��$���� k�)���t��M��Qf���qce-uʟ�T����4�7r�Z�&5@[�G9�&�Qoӻ���� B��ũ���`��t8�ԇ�6��*�Ka������: ��`�ϵ�u܇�V(�k	Y��r�_��c�
���P;�m�%+aj���-��/cg��>`��[�G      �   K   x�]˱�0�X���%l�ʠ��!&ݛ#��R��	�:��8�����S��?��,w�bN*>.,�Ow��5      �   [   x�3�J�IL���K-V(�O�L�I-�2��t�2�tM)MJޜ�������W���e��ZQ�������e��[P������������ ��      �   �
  x��Y�n�8]����UTW9��A&�`���v�g�K��4$RMJ�T���Yx����9��R����2� %�"���s��&�wN�ʉҖ���LTNy���noM:z]��fXP"���b��Y(��$���֋%����P^\�6抶벲��&�݊�^v�����_����F��48SB�C$���E�!~x���i2���jޘܮ�����J�ۺX�Z��G/��EAZ�N.��xz���Bf�^�5�Mr����3Ef���4�Bg��U�lh�L+��	,:+.��d��k3Br���4#]�ٲ�d�5;`���ԅ�i�t4��B��)�ov��B��J�B_7������z��5�ge`�U3:5/�?�`�x�{$�RLg�AgO�/,���x�R5�o��ͭO?���pO�]�y��s���غl������@�##�*�eH���Be5ި�׀��c,��H_m���v(�Ɛ��?�N"��Z���q��z>M��-��ִ�� ���������|�6����#���.���mpY��ɱ(q&�w��s� WN	;��-�����ɕ���1p]�4��L	-^6rFdP���5zV���T!3u&.��gO�|��1]ڦƹ)��䣬�˿/���x������_���~�N�=.����
�;�b��(Y�7&�eE�d�{;s�Ž���.h���>Z�&��`ȱir::�qk�G�����^�ǽ#Y�����az���/G���A���go�rW���"9k$����s�v�d���pV�R���хa*���6��f/�R���?kMW�<�ӓ7�����p�t+_yU�,R�9������RE{�5,B�(F'�q�̬���K�k�Eh �C��:D���FS� �/�y�z%�_�G�����X���-�0	�R�9���9^ڙtd}ZxFK�׌��	�XzER��N�ӰX5љo�������
��& ��
JuG�\��?霗���'�*�%8��[kM��u��{s_�����(xi�@6��[.���� �L��-��w��8Xb͘g6�Οi�_m@�1!W΂�զ�"�pDX�@B�Z�LpY�/IH���2R�c���¯�p�����`(��Pk�s�V+2�\*���!��JErۮoR��p��� E~@Y�cFV�����l�J���\�#Kbm���_Q)[&$�L�۠}�L�p�F�^ݑ�8�r��Wr��Ȃ2^i���M9�#܊��j��9Pi��$Q_B��$�(c�OVyɯ�L>ϐ>#1ɂ�Ւ7r�@�-�gc�s�6X�.�Md5פ��҂d�C�:d�Lэ�8�^�*�1�4��F[b7e3~�=oq��WE��v��ښ�����I�\Ϛ�� �pc`��ؐ�(�*��s��"�����;��-��Q�#�BM�N��\5*_�^\!]�.�Ć�����8+�v4A!�>���^�r~I[��1t�G#�g����S�����0�@�����T��eE9J(���u�����S��,�[�
m(�o��#��M�+
�R�ON Ҟ� ��9k�Ɯ��.�NA�|^K2CG���v��
�b�Ĉ������w�Cc~��)f�3#��� ����s,���P��+��!:W��_�;~�V��?�<�⛈�qK�����qʗ���ñ������-51�����4F��8�`�R�0�}��O!GEJ�R�;	t�T%F���R-��Oa�r�e��Ȏ�I��u���E7G�H$����@u�eۋ�*"�v1g�1�Q]��*l|�MˍCH
A"$
G���,�5a3�'�pFU2��J����&�����Fm��U{�g�@����Б���no��C��/\��N��"�q��Q$��1��;ZA�`4�zr_�#uy�x�ƾk%/��Ŭ�ƭ����R2���{��í͎h)�?)�Fp������࿪��+�I7�0����y�Y2�B���` s��k�0Z�� ��oh�P(D�[㎮���}���ZbW��<��ή�VW*SW3�%Q(�=U5�u�ޒ��hzdV�πc��]6 �^_j�Us��F��`� U��ɧ��/l�����1Үhٕb�S�Y�恃!
"ڑ�4�UB����׾�%�>����1Y3ч�0m�Q�M0xx��Kż�u{�QK��i��@[*~�7�ا���s5S�
L�W&ӓ��{ė�	�dz
�����.z�S�Ą;]�tg������G�xf� ǽSø��O�j�G�5�[�
���5]o��#KP�0ð��$MuS�d��t2T�����ht�������9?x|�U/����#�ryw��W{̔�5/圦v���ߞ&�3I�5QN7O�#S��q�|tH(zpsp4�c�T%��9}�7�#�yC��UH�K9
�jhT	Ԏ�=D�'CקBqҡ&�i��*�8$sl�^��^��}8O��}�sS��_sɝRj_vs�w{=�ypP�6Ɋ>-W\L�#��B#:	�@Mu	�R��MG��}7/���n�� b1����
d���B�-5+ҭ�9�El����o����	�\W��]8���]o�&͕��A#��:�`o9��=�(��#ϕ9w�ۗ��x C�ְ�}�=�U�M �g�!9:"��;�袢h�'U�7L��݆���\�>��3C�Y�l���"��1�w�	����P�TPi_��uSP��	uә�������/��.�$� �?ϙ�{`mH��˭L.y~��4������4��&I�_}`�l      �   Q  x�U�AN�0EדS�A�I�e)�RETPWl2� 7�����Nы1MD[������v^{c=��:�{5�Em����#Uq > �20Z�Tc�h�� L��Ԯ,���m�=�W��0�Պ6M<۠<Nu�?�8��,��c^d��?�j�jEXu�ԫj����٣�{�pPPladnr����3G�	8�����r�we�O#C>18�
:�)��Ww�kj�����B�N���d�dQ�ڢ����5�`��nB�����ql>�Sj�J�����jA-PI��.�<E�QY���ޣ+���d���M�9m(�w���'?~��M�$���      �      x�]P�N1�������
;]+��ŗ�UP.>��	�o:��c�;�"���{~�ڬ�&*�q̕����X3�	��!ĎXN(��P�K��ԏBY�jܭk�3�<�Ϝ�8��l3xCf4�z���A3Th���r�Hc�}ԝo�_���t�B�q�#���g�r �n�(P��z�1�1����ױ��>d[��E]B�TN�VQ�W6��9,�mLcґ�=������d�g���{��κK����Z��V�?         @   x�3�(JM-N��I,�2�t:��839Q��(37�(3��.T��\��T�T�MMJ��qqq 
�         H   x����0��b�^!��]��5lٜo����^�eM*��=�,5Đ��FN��`�o�6|b-��{����X         D   x���	�@��b¹{����_G�� cE�I4��Ţ��YO���#�Ǝ����A�7O�9�����6~      �      x�}Z�r�ȕ]��Y�Q��ԃG�1��C3����&�*&@V' J��2�вZ8����?��9�f&�"�ڪ�Jd�������lqz\|���ʡ�1Em�Ҵ��}k���5E�kW���UW�o�]��wu�5���}x0��3����_��c[���}����rXm���,V�������m�[[��f�liۂ;nm��m�ycV���V�_�f�COyL��L$�A��[�3��b�ζ�0�fhy^m���$Blmh Y�Z@��a�?��}w�8?98���D"��'fV߻;=��Ƕu��y*��K6k��1�z��my��R�:����6`�i�f�[�w_JWSz+"�C�s	�:|m���}�*�%0"�q������� ��^��Ά;<e�D�7����Q�C��-{_�=����KF�/��׶�������+��8�4�ʩghP����u��ɢ�3���׈bZ'�N�}�=n[aIp+� ���.�P4!�L]�v�-���[1�JÐ���T�C�Cw_��G��v�h�;g�1ԬH�����}mm2�"�5�4�;���6X��f�o�._��i!֟Dn�A_�&\۷�^����w��W[~Q�m���<r6;^��\�)g�ʫ}ؘ�����B�M�b��D��iZ/*���a+�����$�9��C��k7`�`�VRŶ�_�i�F�����W�x5fW���]$P����A�xX����a
�ly�a�\o�{A%6�]E���vI����4
�$���D J�y��i�J�p�g�����p!nx�#����䒅
���qIj�b���YӀ��\g��a/���Hd����ū�5�*����c���?����X�zzc���������/+էs����p^+���6�����BA`����['����o��n����?�s��N,0���r@���n������,�� ZBH���9��?B�����
fa��$P�o�?El�V�e�������t�l����o���@>��2%MoXq�Ϛ�U�CSWv���@�"N<�4
eV��w؍��ٮ,7
P�P�6�J�2D��<�^a����1�N��ʄ6J��H��0D�fkz >BG�*�@��ڭ�,!�% �1��g�WD���؄:䃉��"	�)½�Y����J#5'�}
�U-�p�(��H��-"��K��u��gMh��$��f�7˳��j����
��a�����'�?`�sN��F�p	�Q���_���>p���aXBzu�ӂE�2�1��=4D�Sc	\A\m��-z_	�3&���}�*bq)k�� ���sF7����5�r�o����t��}&�0t�mc\��
�G#�T2A�~F2�H�&9�OD���2��]��˽㷶Ҩ��-����9��Ӝ��BeK01� �Ԛ����LI���+KU!�1*�ֵ�B�ey�z��l�N����&lL4D}��`������:���Q3���#h�|0��gnv �	'�\��G~����cJ�q²�3��"��B�CT8��=touQŅ	��]��dcS&&�}���{��HB�[���'#�&ӽ�jD_�aЈ��^"d@�;���0��UI�)��5O��),�&�06)��e<|Ao��9�G �]������ь�T)�k�?-}�h�0Y���F"j����mGj	�^"�mۙX�J����(c�t j"E�ba�P8�M�Hu_32R$P�$m�i�����C�"�v%�qq�V[J���󉴺�H��x¶��վ�����w��1lS�K�IH`+K��F	8�N@Ap���	�p��G��զ���5�FS%B��Z�?����w�Ue�0��ء�����|ʬ��s��5r�I0ד�HXr�:n��v�k��5ZJ�,t�l`t���v#�z�5j���Rɹ3,qz25�f�(��}�
��y-�q�$Km/��IF
 %�MI�(�bM�u�K	�(�j�w����۔j�D12�FW��r��@a�Į�l��������cr_���n�8F�2f2o��X��%�<�4��9�˓�W��mb&c�@����Gj[E��ާ4��a�@�nJ���m�&�v���,� um��R��3�^��-��A�Q�86��3$B�֚��C�`���N��֢��0W�[��o����EN��-�F>A�7�͐G
.��=oK*�G	�o���7bo�xe��>�[3{�������
�ӕ���J	-�y�����T�Sk��k�~�뭑.-�2Ez�\�1OL|= 1����2:kӸ�c[�m�h{�A����90m*6����x�2_<��?�ůf�\�����򷑘ֲ�%�H��L��*vD:��pX^f'��n =A���S<`�c.N�3���d�*�T��<��f��8��l�HD��
�e���KFO'9�<�OC�H)"�'��e=D�T+ՂQ�� d/#@���4�T�I���J��$�ՊO��spz!9�{����?d���d4�G���a��I&IoI��[h�V�Jܦ�c��2V8	�L��� Q�=X�)%�������6'�=)�rL�J���K9h��I!�i"�m�H��,�I|P�I��M��g0�Q�X�(�IsI`��u�y� �c���Q�Y>G	(�AI��O����G�W�D�e[�kS�P�.��>�ef�[��JW�k�T� ��cݕo]'�/���<�b����'��
F�e/�2t�Q3�j23�Gv�q+�J�x�^-��ݲ�l�G�a(,�l�8��?�sq&��P�dX�_~:��j��;��*�>ʏ�)B�F��Z`���Nb@&�+t��ב=�>,ot���| 4l�d�� ?��}�?���>�����0)�d�b����l�N�N�~ӺGS�Q�l�8yf�ȸr0uW8	���*ònn�!B��D����X�g��6�<w�6�j^������n��>!� 0��h���3�_���/��ll����I��v�t��	�Ld.g�809
A-�z������%���[���vv�#�����U���XB���8(6�/���MA'�i��G��o��A7AHBrW��9X�R: ��\`��|	$ �RY>�,|� F�d��m�C{�)�%(cF�'�i�y���m����>r�/��ow2�����o��z��4����8��S/�������&?���;��o�37|G$6�&Y��Xzv6$<,���pT���g'���n#��]en��SCg�<AͿ#�z2޼�no2Y�i�mqd�I�I��:���Y\�f�{6K�5Ze�� �M=����|0:n���?�1���2�2�����7�V!er��F"�>��z��fԦ�~�'H���Z�pX9^���6( V� E�&��س�%�}­x���iC�ȁn��Q���	�S����iga����Gח22����� ��Sp����3K@,��Zw���qx��r/2`�r*�23���s�NBz�0�^��_6�m��>���ۧ
e�yS�6/���Z�<�y���>o�=?N��g�ɨn����+�el������ǒi��+|��¯	M5]#+�L�ޗ��G�� ��A u6�dy�qj��ʐ�]�?x���������uqt�x��~��ߓ�(�CV��;OQ��{XN�ÿ���1�~�3�NR�5X��cl��#�wۜ����-}�����p���a+��)�)��c��������U�.��O	�g�E|+d�8}���h�'�.�[bc����� ��/MH;�M�eG�}��*�%r7���������Y�GR��l֥[WJ^x����x��x���t?��Np�6!>��9W��5m�^�ILaf*��Cd�L,�m!�~�1M%Jqb����Fn)N��zy���o���%���#����:?ш����e����^�^ֱ8F��e��'�Cp+stM���U�Z�ׄ�������ç�	�Ǳ�Dk�`�;�xQr��T�1��5����-���S �  �?]���Ds I�].�ƹz��e��-F%��e�{VYBƑ����3�����f���\o%c���rmY�ݳԏO����O fl�'��Pb^:aZƹGU��R�@)�S���ou�1�����~r2
���0�]KB�,�J0�eJ'#ںXQM_��*�k6�{qce���/��^b��ڜ�ĸ��҉W�eqË�x3�1^��4���=��٧O��g?#�$�_��Ƶw(�����s���H�՘�J�Rs�L��t�Xe��~z�g�WOa��G�ݸ9sͣ����-*��ݫ�!�o:֥8�
�����XF�"��\{��[�!��M0�����T�N�kjb1���r2�b�����l�q�	:#-؅��a�m��������Ï��6���      �   u  x�m�Mn�0���� -���˨U���E�U7L�#`�!�r����� i�,���xofӗ�Dc2�d"U��X�Y��|m{ug�
��m��@�]��`+�$SL�,QB)��F'��;��_bW:�g��nK��Pbc��L�Xz/�L��!�_��k����~t��Dͦ�J"��PN�Kl����1�m݈��)�3�r)L.�٦��U����u�<��q���#oN�Y�I�ZW����T�j����f�D2��
",$�>�e�vb������U���ݻ��:o���|������q����i(��oc{_o=��Թ�VvP��6$-hs˵)R�i:�X�El���ɶ�чAE�Q��ʿ)      �   �  x��T�n�H�f��(��lHH���dA	-m*��"���L�g��1Ĺ��^�z��#�&}�=6!�UԽ�l�9�����*��׈t�R�D1B�*��o�{�V�2B�O�z:�ƨ�4��A04���1ֆ�^�Υ�ux�X,r��,��P'�t����i���ά{?{w�~�CpKNW�F�:m �JFZ��gW��Fe���ĸ�ӷh!�
bm�Q�Vm�/1�!-�7����E�\'��A\��l�:h����X��R̝�ˇT��VR��f1n���mEH�x�lJ{/�Ӡr�X+-���8����0�灓4sE*�5|	�r�M�/�[Ɉ�����XR�]��i�Z��$��y՜%&���;�/l9��Vx�]$�i	o6��{��H��5�:)�[0�fdRd��(劓�u^����襳q�1g�Օ���h���?��`�������{°� �������ܭ��h �y�{����B�Qt�%�Ȯ��$W-�J�y�߼��[�ŝz�.\��t.�)�3+�������y��6�J���|��,�
��r�"7�|@QA
���)b)�=��MYx�f��+ɮ���%��f<e��Vv�h��RZ�'R�=��+��u�2c����W�U����%nAu��f�י�r�((�C-���������>�\ZW̳��!M
v�z�G����V��Ʂ����G���_���?�e�A&ˇ	)���ێXQFW�c����_�,H��z,ʐR�m5@+C~���ռ����E�x=�Y�zԒ����_WRqU��l�`�xi��$�J@@I�#DTސ�3�U�������8�5?u���7d/����ܾ�ـє���u�j�
ҽ���.�M���9B��C�      �     x�%�Q�� C��b���^����:�S#�ƺ�%�x!�ͱ�cݸ*�R9h;�x|^��k��VV�d�����ʵg^�&_���[����+W*D4"B;�P��h|G�Jo�!�\������)7q�E�"q�ɉy\A�dIhA�g�΋Z��F��!-2�M�!�iIe�Lx"br��	4�XZ�����派�9GČ)DG���A�������C��ln����_$bGR�?���1'i�:.�!R���7o���}��? �0�Y8      �   C   x�3�t��/J��KL�/RpLNL9�2739�ˈ�%?95�$U���ڔ�".c�@J��cA~e>W� Y5         h   x�-��� E���*�@a �����'�k�S�7�,��}[��s�bY���\{
��Y�[h�*TNX(In��T�*��-���|'u��(�      �     x�]�MN1���)|��)�u��$�n٘ĭ�2�(?#8��bxB�E��߳�K,%OY����3a�#��\z�HX��Ѫ��	S�R�h5� ��������D�֞Y�}� ��I��$R���R����j���A�����]�<Iw��J�!k��%�&�rQ)���y��r�'cK�Ѩ������yV[���*�Z%/y僱����TU�eG�{��X�?4�f�ߤ��*�3��;���ԃ��|+���~�I���Ś!r�3��bixj�e�؍y�1�� �ޏq     