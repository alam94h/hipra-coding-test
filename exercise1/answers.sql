-- a. The region Name

SELECT NAME FROM regions;

-- b. The average sales per employee for the region (Sales per region / Num of 
-- employees) 

SELECT
    r.ID,
    r.NAME,
    SUM(s.AMOUNT) / COUNT(DISTINCT e.ID) AS AVERAGE_SALES_PER_EMPLOYEE
FROM
    regions r
JOIN
    states st ON r.ID = st.REGION_ID
JOIN
    employees e ON st.ID = e.STATE_ID
JOIN
    sales s ON e.ID = s.EMPLOYEE_ID
GROUP BY
    r.ID, r.NAME;

-- c. The difference between the average sales of the region and the highest average 
-- sales, and the average sales per employee for the region.

SELECT 
    r.NAME AS NAME,
    IFNULL(SUM(s.AMOUNT), 0) / COUNT(*) as AVERAGE_SALES_PER_EMPLOYEE,
    (
        SELECT 
            IFNULL(SUM(s.AMOUNT), 0) / COUNT(s.ID) as REGIONAL_AVERAGE
        FROM 
            regions r
        LEFT JOIN 
            states st ON r.ID = st.REGION_ID
        LEFT JOIN 
            employees e ON st.ID = e.STATE_ID
        LEFT JOIN 
            sales s ON e.ID = s.EMPLOYEE_ID
        GROUP BY
            r.NAME
        ORDER BY 
            regional_average 
        DESC
        LIMIT 1
    ) - (IFNULL(SUM(s.AMOUNT), 0) / COUNT(*)) AS DIFFERENCE
FROM 
    regions r
LEFT JOIN
    states st ON r.ID = st.REGION_ID
LEFT JOIN 
    employees e ON st.ID = e.STATE_ID
LEFT JOIN 
    sales s ON e.ID = s.EMPLOYEE_ID
GROUP BY
    r.NAME