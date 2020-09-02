package io.pgs.cmn;

public enum ServiceStatus {

    Successful("0", "OK"),

    // #1000 서버측 오류 (알수 없는 오류)

    // #2000 권한 오류

    // #3000 서비스 오류 (정보 형식의 메세지)
    MSG_3001("3001", "데이터 등록에 실패하였습니다."),
    MSG_3002("3002", "데이터 수정에 실패하였습니다."),
    MSG_3003("3003", "데이터 삭제에 실패하였습니다."),
    MSG_3004("3004", "데이터 저장에 실패하였습니다."),
    MSG_3005("3005", "이미 등록된 데이터입니다."),
    MSG_3006("3006", "주차도면 저장에 실패하였습니다."),

    // #4000 요청 오류
    MSG_4001("4001", "필수입력 항목 값을 확인하세요.");
    ;

    public final String code;
    public final String msg;

    ServiceStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}