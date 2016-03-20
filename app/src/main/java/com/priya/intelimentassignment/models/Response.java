package com.priya.intelimentassignment.models;


/**
 *
 * This class represnts a response which hold 3 parameters.
 * <b>1.</b>A boolean value represnts <b>isSuccess</b>
 * <b>2.</b>A String represnts <b>message</b>
 * <b>3.</b>A Object represnts <b>data</b>
 *
 * @author Priyanka P
 *
 * @createdOn March 20, 2016
 *
 */
public class Response
{
	private boolean isSuccess;

	private String message;

	private Object data;

	public Response(boolean isSuccess, String message, Object data)
	{
		super();
		this.isSuccess = isSuccess;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess()
	{
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess)
	{
		this.isSuccess = isSuccess;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Response [data=");
		buffer.append(data);
		buffer.append(", isSuccess=");
		buffer.append(isSuccess);
		buffer.append(", message=");
		buffer.append(message);
		buffer.append("]");
		return buffer.toString();
	}
}
