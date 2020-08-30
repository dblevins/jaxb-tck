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

package com.sun.tgxml.tjtf.tools.options;

/**
 * ParseArgumentException - 
 *
 * <b>ParseArgumentException</b> is the basic Exception class for 
 * command line argument parsing<br>
 * Signals that an error has been reached unexpectedly while parsing
 * command line option. 
 *
 *
 * @version 	1.0, 19/03/2002
 * @author      Dmitry Fazunenko 
 *
 */


public  class ParseArgumentException extends Exception {

    /**
     * Constructs a ParseArgumentException object.
     *
     * @param message  a an exception message string.
     */
    public ParseArgumentException (String message) {
	super(message);
    }
}
     

