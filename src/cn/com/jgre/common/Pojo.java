package cn.com.jgre.common;

import java.io.Serializable;



public abstract class Pojo implements Serializable{

public String getEntityId()
{
    return null;
}

public Pojo()
{
    sortOrder = -1;
    timeTag = -1L;
    reservedInt1 = -1;
    reservedInt2 = -1;
    reservedInt3 = -1;
    reservedInt4 = -1;
    reservedInt5 = -1;
    reservedInt6 = -1;
    reservedInt7 = -1;
    reservedInt8 = -1;
    reservedInt9 = -1;
    reservedInt10 = -1;
}


public long getTimeTag()
{
    return timeTag;
}

public void setTimeTag(long timeTag)
{
    this.timeTag = timeTag;
}

public String userLogDescription()
{
    throw new UnsupportedOperationException("Must override");
}

public String systemlogDescription()
{
    throw new UnsupportedOperationException("Must override");
}

public String getSearchStartDate()
{
    return searchStartDate;
}

public void setSearchStartDate(String searchStartDate)
{
    this.searchStartDate = searchStartDate;
}

public String getSearchStartTime()
{
    return searchStartTime;
}

public void setSearchStartTime(String searchStartTime)
{
    this.searchStartTime = searchStartTime;
}

public String getSearchEndDate()
{
    return searchEndDate;
}

public void setSearchEndDate(String searchEndDate)
{
    this.searchEndDate = searchEndDate;
}

public String getSearchEndTime()
{
    return searchEndTime;
}

public void setSearchEndTime(String searchEndTime)
{
    this.searchEndTime = searchEndTime;
}

public String getSearchCondition()
{
    return searchCondition;
}

public void setSearchCondition(String searchCondition)
{
    this.searchCondition = searchCondition;
}


public int getSortOrder()
{
    return sortOrder;
}

public void setSortOrder(int sortOrder)
{
    this.sortOrder = sortOrder;
}


public String getReservedText1()
{
    return reservedText1;
}

public String getReservedText2()
{
    return reservedText2;
}

public String getReservedText3()
{
    return reservedText3;
}

public String getReservedText4()
{
    return reservedText4;
}

public String getReservedText5()
{
    return reservedText5;
}

public String getReservedText6()
{
    return reservedText6;
}

public String getReservedText7()
{
    return reservedText7;
}

public String getReservedText8()
{
    return reservedText8;
}

public String getReservedText9()
{
    return reservedText9;
}

public String getReservedText10()
{
    return reservedText10;
}

public int getReservedInt1()
{
    return reservedInt1;
}

public int getReservedInt2()
{
    return reservedInt2;
}

public int getReservedInt3()
{
    return reservedInt3;
}

public void setReservedText1(String reservedText1)
{
    this.reservedText1 = reservedText1;
}

public void setReservedText2(String reservedText2)
{
    this.reservedText2 = reservedText2;
}

public void setReservedText3(String reservedText3)
{
    this.reservedText3 = reservedText3;
}

public void setReservedText4(String reservedText4)
{
    this.reservedText4 = reservedText4;
}

public void setReservedText5(String reservedText5)
{
    this.reservedText5 = reservedText5;
}

public void setReservedText6(String reservedText6)
{
    this.reservedText6 = reservedText6;
}

public void setReservedText7(String reservedText7)
{
    this.reservedText7 = reservedText7;
}

public void setReservedText8(String reservedText8)
{
    this.reservedText8 = reservedText8;
}

public void setReservedText9(String reservedText9)
{
    this.reservedText9 = reservedText9;
}

public void setReservedText10(String reservedText10)
{
    this.reservedText10 = reservedText10;
}

public void setReservedInt1(int reservedInt1)
{
    this.reservedInt1 = reservedInt1;
}

public void setReservedInt2(int reservedInt2)
{
    this.reservedInt2 = reservedInt2;
}

public void setReservedInt3(int reservedInt3)
{
    this.reservedInt3 = reservedInt3;
}


public String getOperatorId()
{
    return operatorId;
}

public String getOperatorName()
{
    return operatorName;
}

public void setOperatorId(String operatorId)
{
    this.operatorId = operatorId;
}

public void setOperatorName(String operatorName)
{
    this.operatorName = operatorName;
}



public int getReservedInt4()
{
    return reservedInt4;
}

public void setReservedInt4(int reservedInt4)
{
    this.reservedInt4 = reservedInt4;
}

public int getReservedInt5()
{
    return reservedInt5;
}

public void setReservedInt5(int reservedInt5)
{
    this.reservedInt5 = reservedInt5;
}

public int getReservedInt6()
{
    return reservedInt6;
}

public int getReservedInt7()
{
    return reservedInt7;
}

public int getReservedInt8()
{
    return reservedInt8;
}

public int getReservedInt9()
{
    return reservedInt9;
}

public int getReservedInt10()
{
    return reservedInt10;
}

public void setReservedInt6(int reservedInt6)
{
    this.reservedInt6 = reservedInt6;
}

public void setReservedInt7(int reservedInt7)
{
    this.reservedInt7 = reservedInt7;
}

public void setReservedInt8(int reservedInt8)
{
    this.reservedInt8 = reservedInt8;
}

public void setReservedInt9(int reservedInt9)
{
    this.reservedInt9 = reservedInt9;
}

public void setReservedInt10(int reservedInt10)
{
    this.reservedInt10 = reservedInt10;
}



private transient String operatorId;
private transient String operatorName;

private transient String searchStartDate;
private transient String searchStartTime;
private transient String searchEndDate;
private transient String searchEndTime;
private transient String searchCondition;
private int sortOrder;
private long timeTag;
private String reservedText1;
private String reservedText2;
private String reservedText3;
private String reservedText4;
private String reservedText5;
private String reservedText6;
private String reservedText7;
private String reservedText8;
private String reservedText9;
private String reservedText10;
private int reservedInt1;
private int reservedInt2;
private int reservedInt3;
private int reservedInt4;
private int reservedInt5;
private int reservedInt6;
private int reservedInt7;
private int reservedInt8;
private int reservedInt9;
private int reservedInt10;
private static final long serialVersionUID = 1L;
}



