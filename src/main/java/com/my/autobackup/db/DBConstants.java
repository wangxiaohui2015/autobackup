
package com.my.autobackup.db;

public interface DBConstants {

    public static final String BACKUP_TASK_INSERT =
                    "INSERT INTO backup_task VALUES(?,?,?,?,?,?,?,?,?)";

    public static final String MANIFEST_CLEAN_TABLE = "delete from manifest";
    public static final String MANIFEST_INSERT_PACKAGE =
                    "insert into manifest values (?,?,?,?,?,?)";
    public static final String MANIFEST_PACKAGE_INFO = "select * from manifest where checksum=?";

    public static final String HISTORY_INSTALLED_PACKAGE =
                    "select * from history_detail where checksum=? and status='completed'";
    public static final String HISTORY_INSERT_PACKAGE =
                    "insert into history (checksum, title, description, type, version, processkey, optionallySelectedPackages, targetPackage) values (?,?,?,?,?,?,?,?)";
    public static final String HISTORY_INSERT_HISTORY_DETAIL =
                    "insert into history_detail (checksum, status, starttime, endtime,loglocation) values (?,?,?,?,?)";

    public static final String HISTORY_GET_HISTORY = "select * from history where checksum=?";
    public static final String HISTORY_UPDATE_HISTORY =
                    "update history set optionallySelectedPackages=?, targetPackage=? where checksum=?";
    public static final String HISTORY_GET_ALL_HISTORY = "select * from history where type=?";
    public static final String HISTORY_GET_HISTORY_DETAIL =
                    "select * from history_detail where checksum=?";
    public static final String HISTORY_DELETE_HISTORY = "delete from history where checksum=?";
    public static final String HISTORY_COMPLETED_HISTORY_DETAIL =
                    "select * from history_detail where checksum=? and status='completed'";
    public static final String HISTORY_CURRENT_HISTORY_DETAIL =
                    " select * from history_detail where checksum=? order by id DESC limit 2;";

    public static final String PKGPROF_GET_PKGPROF = "select * from pkgprof where checksum=?";
    public static final String PKGPROF_GET_BY_FILENAME = "select * from pkgprof where filename=?";
    public static final String PKGPROF_GET_ALL_PKGPROF = "select * from pkgprof";
    public static final String PKGPROF_INSERT_PKGPROF =
                    "insert into pkgprof (checksum, filename, type, helpurl, pkgdef, callabledef, sysinfo, filemetadata) values (?,?,?,?,?,?,?,?)";
    public static final String PKGPROF_UPDATE_STATE =
                    "update pkgprof set status=?, selectedoptionalpkgs=?, templocation=?, timestamp=? where checksum=?";
    public static final String PKGPROF_UPDATE_OPTIONAL_PKGS =
                    "update pkgprof set selectedoptionalpkgs=?, timestamp=? where checksum=?";
    public static final String PKGPROF_REMOVE = "delete from pkgprof where checksum=?";

    public static final String CALLABLE_PKG_GET = "select * from callable_pkgs where checksum=?";
    public static final String CALLABLE_PKG_GET_ALL =
                    "select * from callable_pkgs where target_checksum=?";
    public static final String CALLABLE_PKG_INSERT =
                    "insert into callable_pkgs (checksum, target_checksum, status, timestamp, templocation) values (?,?,?,?,?)";
    public static final String CALLABLE_PKG_UPDATE_STATE =
                    "update callable_pkgs set status=?, templocation=?, timestamp=? where checksum=?";
    public static final String CALLABLE_PKG_UPDATE_OPTIONAL_PKGS =
                    "update callable_pkgs set timestamp=? where checksum=?";
    public static final String CALLABLE_PKG_REMOVE =
                    "delete from callable_pkgs where target_checksum=?";

    public static final String PKGREPOMONITOR_GET_PKGPROF =
                    "select * from pkgrepomonitor where filename=?";
    public static final String PKGREPOMONITOR_GET_PKGPROF_BY_CHKSUM =
                    "select * from pkgrepomonitor where checksum=?";
    public static final String PKGPROF_INSERT_PKGREPOMONITOR =
                    "insert into pkgrepomonitor (filename, checksum, status, note, updated) values (?,?,?,?,?)";
    public static final String PKGREPOMONITOR_GET_ALL_PKGREPOMONITORS =
                    "select * from pkgrepomonitor";
    public static final String PKGREPOMONITOR_REMOVE =
                    "delete from pkgrepomonitor where filename=?";
    public static final String PKGREPOMONITOR_REMOVE_BY_CHKSUM =
                    "delete from pkgrepomonitor where checksum=?";
    public static final String PKGREPOMONITOR_UPDATE_STATE =
                    "update pkgrepomonitor set status=?, checksum=?, note=?, updated=? where filename=?";

    public static final String MATRIX_INSERT_PACKAGE = "insert into matrix values (?,?,?,?,?,?)";
    public static final String GET_PACKAGE_MATRIX_DETAILS = "select * from matrix where checksum=?";

    public static final String AVAILABLE_CHECK_PACKAGE =
                    "select count(*) from available_pkgs where checksum=?";
    public static final String AVAILABLE_INSERT_PACKAGE =
                    "insert into available_pkgs values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String AVAILABLE_REMOVE_PACKAGE =
                    "delete from available_pkgs where checksum=?";
    public static final String AVAILABLE_CHANGE_STATUS =
                    "update available_pkgs set status=? where checksum=?";
    public static final String AVAILABLE_GET_STATUS =
                    "select status from available_pkgs where checksum=?";
    public static final String AVAILABLE_GET_PACKAGE =
                    "select * from available_pkgs where checksum=?";
    public static final String AVAILABLE_GET_PACKAGE_BYFILENAME =
                    "select * from available_pkgs where filename=?";
    public static final String AVAILABLE_GET_PACKAGE_BYOPERATION =
                    "select * from available_pkgs where operation=?";
    public static final String AVAILABLE_PACKAGES = "select * from available_pkgs where type=?";
    public static final String GET_CATEGORY_NAMES =
                    "select distinct category from available_pkgs where type=? order by category asc";
    public static final String GET_CATEGORY_NAMES_EXCLUDE_SUPPORTONLY =
                    "select distinct category from available_pkgs where type=? and support_only=? order by category asc";
    public static final String AVAILABLE_CATEGORY_PACKAGES =
                    "select * from available_pkgs where type=? and category=?";
    public static final String AVAILABLE_CATEGORY_PACKAGES_EXCLUDE_SUPPORTONLY =
                    "select * from available_pkgs where type=? and support_only=? and category=?";
    public static final String AVAILABLE_STATUS_FILTER_PACKAGES =
                    "select * from available_pkgs where type=? and status=?";
    public static final String AVAILABLE_STATUS_PACKAGES =
                    "select * from available_pkgs where (type='server' or type='workflow') and status=?";
    public static final String AVAILABLE_SOURCE_FILTER_PACKAGES =
                    "select * from available_pkgs where type=? and source=?";
    public static final String AVAILABLE_UPDATE_PACKAGE_META =
                    "update available_pkgs set title=?, description=?, version=?, priority=?, grouptype=?, filename=?, support_only=?, category=? where checksum=?";
    public static final String AVAILABLE_UPDATE_PACKAGE_STATE =
                    "update available_pkgs set source=?, status=?, filename=?, processkey=?, tempfolder=?, helpurl=? where checksum=?";
    public static final String AVAILABLE_SERVER_PACKAGES =
                    "select * from available_pkgs where type=? and support_only=?";

    public static final String CREATE_USERINPUT = "insert into userinput values (?,?)";
    public static final String QUERY_USERINPUT = "select data from userinput where checksum=?";
    public static final String DELETE_USERINPUT = "delete from userinput where checksum = ?";
    public static final String UPDATE_USERINPUT =
                    "update userinput set data = ? where checksum = ?";

    public static final String CREATE_USERINPUT_TASK =
                    "insert into userinput_task (checksum,taskname,classname,updated,state,data) values (?,?,?,?,?,?)";
    public static final String QUERY_USERINPUT_TASK =
                    "select * from userinput_task where checksum=? and taskname=?";
    public static final String QUERY_ALL_USERINPUT_TASK =
                    "select * from userinput_task where checksum=? order by updated desc";
    public static final String DELETE_USERINPUT_TASK =
                    "delete from userinput_task where checksum=?";
    public static final String UPDATE_USERINPUT_TASK =
                    "update userinput_task set data=?, updated=?, state=? where checksum=? and taskname=?";

    public static final String CREATE_USERINPUT_PROPERTY =
                    "insert into userinput_property (checksum,name,collectedvalue,defaultvalue,uservalue,xml) values (?,?,?,?,?,?)";
    public static final String QUERY_USERINPUT_PROPERTY =
                    "select * from userinput_property where checksum=? and name=?";
    public static final String QUERY_ALL_USERINPUT_PROPERTY =
                    "select * from userinput_property where checksum=?";
    public static final String DELETE_USERINPUT_PROPERTY =
                    "delete from userinput_property where checksum=? and name=?";
    public static final String DELETE_ALL_USERINPUT_PROPERTY =
                    "delete from userinput_property where checksum=?";
    public static final String UPDATE_USERINPUT_PROPERTY =
                    "update userinput_property set collectedvalue=?,defaultvalue=?,uservalue=?,xml=? where checksum=? and name=?";

    public static final String GET_SYSTEM_SETTING =
                    "select setting_value from system_setting where setting_name=?";

    public static final String COMPATIBILITY_GET_VERSION =
                    "select * from compatibility where component=?";
    public static final String COMPATIBILITY_INSERT_VERSION =
                    "insert into compatibility values (?,?)";
    public static final String COMPATIBILITY_UPDATE_VERSION =
                    "update compatibility set version=? where component=?";

    public static final String ADS_INSERT = "insert into ads values (?,?,?,?,?)";
    public static final String ADS_GET_LATEST =
                    "select * from ads order by lastcontact desc limit 1";
    public static final String ADS_GET_BY_HOSTNAME = "select * from ads where hostname=?";
    public static final String ADS_UPDATE =
                    "update ads set port=?, version=?, lastcontact=?, isADS=? where hostname=?";

    public static final String CREATE_UPDATE_MESSAGE =
                    "insert into update_message (msgid,checksum,progress,taskname,processkey,processinstanceid,taskid,timestamp,endtaskname) values (?,?,?,?,?,?,?,?,?)";
    public static final String QUERY_UPDATE_MESSAGE =
                    "select * from update_message where checksum=? and taskname=?";
    public static final String QUERY_UPDATE_MESSAGE_PID =
                    "select * from update_message where processinstanceid=? order by timestamp";
    public static final String UPDATE_UPDATE_MESSAGE =
                    "update update_message set progress=?, timestamp=? where checksum=? and taskname=?";
    public static final String DELETE_UPDATE_MESSAGE =
                    "delete from update_message where checksum=? and taskname=?";

    public static final String CREATE_UPDATE_MESSAGE_CONTENT =
                    "insert into update_message_content values (?,?,?)";
    public static final String QUERY_UPDATE_MESSAGE_CONTENT =
                    "select * from update_message_content where msgid=?";
    public static final String UPDATE_UPDATE_MESSAGE_CONTENT =
                    "update update_message_content set value=? where msgid=?";
    public static final String DELETE_UPDATE_MESSAGE_CONTENT =
                    "delete from update_message_content where  msgid=?";

    public static final String CREATE_UPDATE_MESSAGE_TRANSITION =
                    "insert into update_message_transition values (?,?,?)";
    public static final String QUERY_UPDATE_MESSAGE_TRANSITION =
                    "select * from update_message_transition where msgid=?";
    public static final String UPDATE_UPDATE_MESSAGE_TRANSITION =
                    "update update_message_transition set value=? where msgid=?";
    public static final String DELETE_UPDATE_MESSAGE_TRANSITION =
                    "delete from update_message_transition where  msgid=?";

    public static final String CREATE_UPDATE_MESSAGE_PERCENTCOMPLETE =
                    "insert into update_message_percentcomplete values (?,?,?)";
    public static final String QUERY_UPDATE_MESSAGE_PERCENTCOMPLETE =
                    "select * from update_message_percentcomplete where msgid=?";
    public static final String UPDATE_UPDATE_MESSAGE_PERCENTCOMPLETE =
                    "update update_message_percentcomplete set value=? where msgid=?";
    public static final String DELETE_UPDATE_MESSAGE_PERCENTCOMPLETE =
                    "delete from update_message_percentcomplete where msgid=?";

    public static final String UPDATE_JBPM_EXECUTION_ACTIVITY =
                    "update jbpm4_execution set activityname_=? ";
    public static final String QUERY_JBPM_EXECUTION_ACTIVITY =
                    "select activityname_ from jbpm4_execution";

}
