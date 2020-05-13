/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.cognos.test.responses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.odpi.openmetadata.accessservices.cognos.ffdc.CognosErrorCode;
import org.odpi.openmetadata.accessservices.cognos.ffdc.exceptions.CognosCheckedException;
import org.odpi.openmetadata.accessservices.cognos.responses.ErrorResponse;
import org.odpi.openmetadata.accessservices.cognos.test.utils.TestUtilities;

public class ErrorResponseTest {
	
	private static final String	MESSAGE = "short message";
	private static final String	CODE = "MSR_OMS_001";
	private static final String	EXCEPTIONCAUSE = "com.ibm.cognos.AppException: Cannot open ...";

	String master = String.format("{%n" + 
			"  \"class\" : \"ErrorResponse\",%n" + 
			"  \"relatedHTTPCode\" : 500,%n" + 
			"  \"msg\" : \"short message\",%n" + 
			"  \"code\" : \"MSR_OMS_001\",%n" + 
			"  \"exceptionCauseMsg\" : \"com.ibm.cognos.AppException: Cannot open ...\"%n" + 
			"}");

	@Test
	public void toJson() {
		
		CognosCheckedException ex = new CognosCheckedException(
				CognosErrorCode.INCORRECT_MODEL_EXCEPTION.getMessageDefinition(),
				"ErrorResponse",
				"");
		
		ErrorResponse er = new ErrorResponse(ex);
		
		er.setMessage(MESSAGE);
		er.setErrorCode(CODE);
		er.setExceptionCause(EXCEPTIONCAUSE);
		
		TestUtilities.assertObjectJson(er, master);
	}
	
	@Test
	public void fromJson() {
		
		ErrorResponse er = TestUtilities.readObjectJson(master, ErrorResponse.class);

		assertEquals(MESSAGE, er.getMessage());
		assertEquals(CODE, er.getErrorCode());
		assertEquals(EXCEPTIONCAUSE, er.getExceptionCause());

	}


}
