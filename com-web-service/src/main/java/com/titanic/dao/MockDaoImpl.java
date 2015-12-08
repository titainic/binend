package com.titanic.dao;

import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class MockDaoImpl implements MockDao
{
    public String getData()
    {
        return "MockDao";
    }
}
