package com.ezderm.solution.exception;


public class EzdermSpringBootRunTimeException extends RuntimeException {

	private static final long serialVersionUID = -6990431254931833111L;
	
	private int subcode;

    public EzdermSpringBootRunTimeException(String msg) {
        this(msg, 1);
    }

    public EzdermSpringBootRunTimeException(Exception e) {
        this(e, 1);
    }

    public EzdermSpringBootRunTimeException(Exception e, int code) {
        super(e);
        this.subcode = code;
    }

    public EzdermSpringBootRunTimeException(String msg, int code) {

        super(msg);
        this.subcode = code;
    }

    public int getSubcode() {
        return subcode;
    }


}
