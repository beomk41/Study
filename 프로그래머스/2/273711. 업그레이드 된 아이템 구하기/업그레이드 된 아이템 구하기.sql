-- 코드를 작성해주세요
SELECT
    I.ITEM_ID AS ITEM_ID,
    I.ITEM_NAME AS ITEM_NAME,
    I.RARITY AS RARITY
FROM
    ITEM_INFO AS I
JOIN 
    (
        SELECT
            T.PARENT_ITEM_ID,
            T.ITEM_ID AS NEXT_ID
        FROM
            ITEM_INFO AS I2
        JOIN
            ITEM_TREE AS T
        ON
            I2.ITEM_ID = T.PARENT_ITEM_ID
        WHERE
            I2.RARITY = 'RARE' AND
            T.PARENT_ITEM_ID IS NOT NULL
    ) AS C
ON 
    I.ITEM_ID = C.NEXT_ID
ORDER BY
    ITEM_ID DESC
    
    
    