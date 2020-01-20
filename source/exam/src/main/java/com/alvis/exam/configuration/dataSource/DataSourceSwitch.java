package com.alvis.exam.configuration.dataSource;

public class DataSourceSwitch {
    /**
     * 保存数据源类型线程安全容器
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     *
     * @param dataSourceType 数据源类型
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static void switchToSqlserver() {
        setDataSourceType(DataSourceDialect.SQLSERVER);
    }

    /**
     * 获取数据源类型
     *
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清空数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
