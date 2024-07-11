DROP SEQUENCE upload_seq;
DROP SEQUENCE file_seq;
CREATE SEQUENCE upload_seq;
CREATE SEQUENCE file_seq;

DROP TABLE file_t;
DROP TABLE upload_t;

CREATE TABLE upload_t (
  upload_no NUMBER NOT NULL,
  uploader  VARCHAR2(100 BYTE),
  ip        VARCHAR2(50 BYTE),
  upload_dt DATE, 
  CONSTRAINT pk_upload PRIMARY KEY(upload_no)
);
CREATE TABLE file_t (
  file_no           NUMBER NOT NULL,
  upload_path       VARCHAR2(500 BYTE),
  original_filename VARCHAR2(300 BYTE),
  filesystem_name   VARCHAR2(40 BYTE),
  upload_no         NUMBER,
  down_count        NUMBER,
  CONSTRAINT pk_file PRIMARY KEY(file_no),
  CONSTRAINT fk_file_upload FOREIGN KEY(upload_no)
    REFERENCES upload_t(upload_no)
      ON DELETE CASCADE
);

SELECT U.upload_no, U.uploader, U.ip, U.upload_dt, F.file_no, F.original_filename, F.down_count
  FROM upload_t U LEFT JOIN file_t F
    ON U.upload_no = F.upload_no;

-- inner join : upload_t �� file_t �� ��� �����ؾ߸� ��ȸ�ȴ�.
-- left join  : upload_t �� �����ϸ� file_t �� ��� ��ȸ�ȴ�.
--              ���ʿ� �ִ� ������ �׻� ��ȸ�ȴ�.

SELECT U.upload_no, U.uploader, COUNT(F.file_no) AS file_cnt -- count(file.no) ���� file_cnt
  FROM upload_t U LEFT JOIN file_t F
    ON U.upload_no = F.upload_no
 GROUP BY U.upload_no, U.uploader;  -- group by ���� ��õ� Į���� select ������ ��ȸ�� �� �ִ�.

-- inner join : upload_t�� file_t �� ��� �����ؾ߸� ��ȸ�ȴ�.
-- left upload �� �����ϸ� file_t�� ��� ��ȸ�Ѵ�.
--group by upload_no , uploader 
