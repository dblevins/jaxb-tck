/*
 * Copyright (c) 2002, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.tgxml.tjtf.processors.taghandlers.impl;
import java.io.IOException;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.sun.tgxml.tjtf.processors.taghandlers.ParserHandlerSupport;
import com.sun.tgxml.tjtf.processors.taghandlers.EmitterHandlerSupport;
import com.sun.tgxml.tjtf.resources.LibResHandler;
import com.sun.tgxml.tjtf.impl.TagsImpl;

import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;
 
/** 
 * ExpectedResultException_TH - The tag-handler for a Description tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    ExpectedResultException_TH 
 * ============================================================================================ 
 */ 
public class ExpectedResultException_TH extends TextStreamTagHandler  {


   /* 
    * ============================================================================================ 
    *    Fields 
    * ============================================================================================ 
    */ 




   /* 
    * ============================================================================================ 
    *    Methods 
    * ============================================================================================ 
    */ 


    //------------------------------------------------------------------------------
    //  Constructors
    //------------------------------------------------------------------------------

   /** 
    *   ExpectedResultException_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public ExpectedResultException_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_expectedresultexception;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag(String text) throws SAXException {
	try {
	    super.endTag(text);
	    Stack testItemStack = m_ParserHandler.getStack();
	    Object testitem = testItemStack.peek();
	    
	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));
	    
	    if (! (testitem instanceof TestCaseSpec)  )
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_testcasespec));
	    
	    //  Nothing is pushed onto the stack
	    TestCaseSpec tcs = (TestCaseSpec) testitem;
	    
	    ArrayList exceptions = tcs.getExpectedResultExceptions();
	    if (exceptions == null) {
		exceptions = new ArrayList();
		tcs.setExpectedResultExceptions(exceptions);
	    }
	    
	    // validated the author name
	    // validateExpectedResultException(text);
	    ExpectedResultException ere = DocumentationFactory.createExpectedResultException();
	    ere.setException(text);
	    exceptions.add(ere);
	} catch (TestFileException e) {
	    m_ParserHandler.throwError(e.getMessage());
	}
    }
 
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
         

  /**
    *   emit the Ref text.
    *  <p>
    * @see #endTag
    */
    public void emitTextFlow(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof ExpectedResultException))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"ExpectedResultException", tdObject.getClass().getName()));

	ExpectedResultException ere = (ExpectedResultException) tdObject;

	m_EmitterHandler.emitText(ere.getException());

    }
     

}
