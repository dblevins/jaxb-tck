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

package com.sun.tgxml.tools.filter.processors;

import com.sun.tgxml.tjtf.api.tests.TestGroup;
import com.sun.tgxml.tools.filter.libutil.TestGroupLibsInfo;

/**
 * Interface of Test filtering component for build tools.
 *
 * @version  1.0, 10/20/2001
 * @author   Ilya V. Neverov
 */
public interface TestFilter2 {
    /** 
     * Removes irrelevant testcases and classes
     *
     * @param      testGroup   TestGroup object to be processed. 
     *             It is modified while the filtering.
     * @param      libsInfo   information about accepted/rejected libraries
     *
     * @return     null   if TestFilter2 instance.
     * @return     TestGroup reference to cleaned TestGroup object.
     * @throws     FilteringException to diagnose problems discovered by 
     *             plugin or standard Test filtering implementation.
     */
    public TestGroup strip(TestGroup testGroup, TestGroupLibsInfo libsInfo)
            throws FilteringException;
}

