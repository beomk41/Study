-- 코드를 입력하세요
SELECT
    C.CAR_TYPE AS CAR_TYPE,
    COUNT(*) AS CARS
FROM
    (
        SELECT
            *
        FROM
            CAR_RENTAL_COMPANY_CAR
        WHERE
            INSTR(OPTIONS, '통풍시트') OR
            INSTR(OPTIONS, '가죽시트') OR
            INSTR(OPTIONS, '열선시트')
    ) AS C
GROUP BY
    C.CAR_TYPE
ORDER BY
    C.CAR_TYPE