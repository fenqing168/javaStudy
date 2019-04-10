package cn.fenqing168.utils.lib;

import com.sun.jna.Library;

/**
 * @Author: 邹施鑫
 * @Date: 2019/4/2 12:38
 * @Version 1.0
 */
public  interface Dll extends Library {

    /**
     * 初始化 DLL 函数,只需要在 DLL 导入后调用一次。
     *
     * @param ipbuff 确定需要访问 IP 的数组地址
     * @param num    确定需要一起操作的本公司以太网数据采集控制产品的个数
     * @return
     */
    int SysInit(int[] ipbuff, int num);

    /**
     * 把本公司以太网数据采集控制产品对应的字符型的 IP 地址转换为无符号 32bit 宽整型数值
     *
     * @param strIp
     * @return
     */
    int IP_StrToInt(String strIp);

    /**
     * DLL 调用退出,在程序退出的时候调用一次。
     *
     * @return
     */
    int SysClose();

    /**
     * @param str_name
     * @param user_code
     * @param socket_index
     * @return
     */
    int NameRead(byte[] str_name, byte[] user_code, int socket_index);

    /**
     * 连接仪器函数。该函数必须在 SysInit()函数执行后才能调用。该函数针对某个
     * IP 的仪器创建连接的 Socket，可以指定本地端口，也可以由系统自主选择空闲端口。调用
     * 该函数成功后，会返回一个该 Socket 在本 DLL 里的唯一编码，调用后面函数，对函数
     * socket_index 参数赋值时候，都需要指定该编码，有了该编码，就可以对该连接对应的 IP
     * 采集仪采取相关操作。
     *
     * @param inst_ip    仪器的 IP 地址，无符号整形。也就是 SysInit()函数 ipbuff 里初始化过的值
     * @param inst_port  指定仪器端接收的 port，这个参数固定为 1600。
     * @param local_port 指定本地连接端口，=0 为系统自主选择空闲端口。
     * @param type       协议类型，=0 为 UDP，=1 为 TCP
     * @param outtime    本连接超时时间，对本连接的后面操作指令都有效。单位 ms，一般设置 100ms 以上
     * @param num        本连接次数为 num+1，用于超时后重新连接 num 次，对本连接的后面操作指令都有效。取 1-255，一般设为 3。
     * @return
     */
    int ConnectCreate(int inst_ip, short inst_port, short local_port, int type, int outtime, int num);

    /**
     * 删除连接函数。该函数必须在 SysInit()函数执行后才能调用。
     *
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ConnectDel(int socket_index);


    /**
     * 同步采集仪 A/D 设置函数，在调用 StartAD()、 ReadAdData()之前必须先执行该函数设置。参数掉电不保存。在多个同步采集产品组成同步系统的时候，必须先设置主控板，再后才可以设置从板。在一个系统中必须有且只有一个主控板，其他设置为从板。如果是每个同步采集产品独立使用，设置为主控板。
     *
     * @param ad_frequency 采样频率，单位是 HZ，是单个通道的频率，不是所有采集通道的总频率。比如要连续采样 3 个通道，要求每个通道的采样频率达到 10000Hz，则这个参数应该输入 10000。
     * @param ad_range     A/D 输入范围，该参数对 SK1218A、SK2011A、SK2011B 时 0—±5V，1—±10V；对SK2013A 时，0-±2.5V，1-±1.25V，2-±625mV，3-±312.5mV，4-±156.25mV，5-±78.125mV，其他产品时，该参数填 0。
     * @param ain_select   模拟信号输入选择，每路占 2bit，=0 为 DC，=1 为 Rtd，=2 为 AC，=3 为 ICP。
     * @param ch_enabled   通道使能，两个字节的位分别对应 1-16 通道。1 表示使能，0 表示禁止。
     * @param master_flag  设置相应的板为主控板或从板，=0 为主控板，=1 为从板。
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */

    int ADSyncParaWrite(int ad_frequency, int ad_range, int ain_select, short ch_enabled, short master_flag, int socket_index);

    /**
     * 异步采集仪 A/D 设置函数，在调用 StartAD()、 ReadAdData()之前必须先执行该函数设置。参数掉电不保存。
     *
     * @param ad_frequency 异步采集时，是总采样频率, 不是单个通道的采样频率，。该参数最小可设为 1，单位为 Hz，采集仪的最大采样频率为 200KHz。
     * @param ad_gain      程控放大倍数，该字段取值跟模块硬件配置有关。0 表示不放大，每增加 1，表示增加一级放大倍数。比如程控放大的倍数有 1、2、4、8，那么输入 0 表示不放大，输入 1 表示放大 2 倍，输入 2 表示放大 4 倍，输入 3 表示放大 8 倍。
     * @param ad_start_ch  采集开始通道，为 0-31 数值，分别表示 1-32 通道。
     * @param ad_end_ch    采集结束通道，为 0-31 数值，分别表示 1-32 通道,这个数一定要大于或等于ad_start_ch，如果为单通道采集，开始通道和结束通道数字相同。
     * @param mux_type     开关切换类型，=0 为单端输入，=1 为双端输入。
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ADParaWrite(int ad_frequency, int ad_gain, char ad_start_ch, char ad_end_ch, char mux_type, int socket_index);

    /**
     * 启动 A/D 采集。在调 ReadAdData()之前必须先执行该函数设置。
     *
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ADStart(int socket_index);

    /**
     * 停止 A/D 采集。
     *
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ADStop(int socket_index);

    /**
     * 读A/D采集数据函数，数据量是可以为 1-232。
     *
     * @param pData        存放数据数组的地址，这个地址由应用程序给，存放数据的空间也由应用程序申请,要读多少数据，就开多大空间。
     * @param nCount       读取采集数据个数（每个采集数据是 16bit），要求是采集通道数的整数倍
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ADDataRead(short[] pData, int nCount, int socket_index);

    /**
     * 24bit采集卡连续采集读数。读取的数据是连续的，数据量是可以为 1-232
     *
     * @param pData        存放数据数组的地址，这个地址由应用程序给，存放数据的空间也由应用程序申请,要读多少数据，就开多大空间。
     * @param nCount       连续读取采集的数据个数。
     * @param socket_index 要操作的 Socket 在 DLL 里对应的唯一编码。
     * @return
     */
    int ADDataRead24Bit(int[] pData, int nCount, int socket_index);

    /**
     * 该函数的功能为 ETHDLL 自动创建一个读采集数据的线程，把采集数据读取后暂时放在内存里,等待应用程序的定期查询读取。一个 IP 地址，只能创建一个读数线程。使用该指令可方便用户在不能创建线程的软件下调用 ETHDLL 读取采集数据。在使用该指令之前，必须调用设置 A/D 参数和启动 A/D 函数。
     *
     * @param addatanum  指定每次要读取采集数据的个数。必须是通道数的整数倍，建议取值少于 10MB。
     * @param addatatype 指定要读取采集数据的类型。=0 为 12—16bit，=1 为 18—24bit。
     * @param inst_ip    仪器的 IP 地址。
     * @param inst_port  指定仪器端接收的 port，这个参数固定为 1600。
     * @param local_port 指定本地连接端口，=0 为系统自主选择空闲端口。
     * @param type       协议类型，=0 为 UDP，=1 为 TCP。
     * @param outtime    本连接超时时间，对本连接的后面操作指令都有效。单位 ms，一般设置 100ms 以上。
     * @param num        本连接次数为 num+1，用于超时后重新连接 num 次，对本连接的后面操作指令都有效。取 1-255，一般设为 3。
     * @return
     */
    int CreateAdReadThread(int addatanum, int addatatype, int inst_ip, short inst_port,
                           short local_port, int type, int outtime, int num);

    /**
     * 该函数为 CreateAdReadThread()的配套函数，用来删除 CreateAdReadThread()对应创建的读数线程。调用该函数前，必须先停止调用 QueryReadAdData()、QueryReadAdData24Bit()，调用该函数后，还必须调用停止 A/D 函数，才算正式完成停止 A/D 采集。
     *
     * @param inst_ip 仪器的 IP 地址。
     * @return
     */
    int DeleteAdReadThread(int inst_ip);


    int GetCountData();
}