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

import com.sun.tgxml.tjtf.api.tests.*;
import com.sun.tgxml.tjtf.api.documentation.*;
import com.sun.tgxml.tjtf.api.exceptions.TestFileException;

 
/** 
 * LibraryDocumentation_TH - The tag-handler for a LibraryDocumentation tag. 
 * 
 * 
 * @version 	1.0, 10/02/00 
 * @author Kevin T. Looney 
 */ 
 
 
/* 
 * ============================================================================================ 
 *    LibraryDocumentation_TH 
 * ============================================================================================ 
 */ 
public class LibraryDocumentation_TH extends TagHandlerImpl  {


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
    *   LibraryDocumentation_TH constructor - 
    *       Initialize our internal fields. 
    */ 
    public LibraryDocumentation_TH( ) {
	super( );
	 
    }

    //------------------------------------------------------------------------------
    //  TagName access
    //------------------------------------------------------------------------------

    /**
     * Get the tag string associated with this handler.
     */
    public String getTagName() {
	return TagsImpl.ctStr_tag_librarydocumentation;
    }

    //------------------------------------------------------------------------------
    //  Handlers
    //------------------------------------------------------------------------------

  /**
    *   Start handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void startTag(org.xml.sax.Attributes attrs) throws SAXException {
	super.startTag(attrs);
	Stack testItemStack = m_ParserHandler.getStack();
	Object testitem = testItemStack.peek();
	if (! (testitem instanceof Library))
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.invcontext", getTagName(), TagsImpl.ctStr_tag_codeset));

	LibDocumentation libdoc = DocumentationFactory.createLibDocumentation();
	Library lib = (Library) testitem;
	lib.setLibDocumentation(libdoc);
	testItemStack.push(libdoc);

    }
     

  /**
    *   End handling a given XML tag.
    *  <p>
    * @see #endTag
    */
    public void endTag() throws SAXException {
	super.endTag();
	try {
	    Stack testItemStack = getParserHandler().getStack();

	    Object testitem = testItemStack.pop();

	    if (testitem == null)
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.nullstackitem"));

	    if (! (testitem instanceof LibDocumentation))
		m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.inconsistentstack", getTagName()));

	} catch (EmptyStackException e) {
	    m_ParserHandler.throwError(LibResHandler.getResStr("parser.error.emptystack.pop"));
	}
    }
     
     
    //------------------------------------------------------------------------------
    //  EmitterHandlers
    //------------------------------------------------------------------------------
         
          
          
  /**
    *   emit a tags components.
    *  <p>
    */
    public void emitComponents(Object tdObject) throws TestFileException, IOException {
	if (! (tdObject instanceof LibDocumentation))
	    throw new TestFileException(LibResHandler.getResStr("emitter.error.invObj", 
					"LibDocumentation", tdObject.getClass().getName()));

	LibDocumentation libd = (LibDocumentation) tdObject;

	String title = libd.getTitle();
	if (title != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_title, title);

	String description = libd.getDescription();
	if (description != null)
	    m_EmitterHandler.emit(TagsImpl.ctStr_tag_description, description);



	ArrayList authors = libd.getAuthors();
	if (authors != null) {
	    Iterator it2 = authors.iterator();

	    while (it2.hasNext()) {
		m_EmitterHandler.emit(TagsImpl.ctStr_tag_author, it2.next());
	    }
	}

    }

}
