/*
 * Copyright (c) 2004, 2020 Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tgxml.util.crn;

/**
 * Macro value parser interface.
 *
 * @author Konstantin S. Bobrovsky
 */
public interface MacroParser {
    /**
     * Defines a substring matching algorithm.
     *
     * @param s
     *     a string to be matched
     * @param start_index
     *     an index in the string where to start matching
     * @return
     *     -1 in case of matching failure, otherwise - the end
     *    index of the matched substring plus one.
     */
    int parseValue(String s, int start_index);
}
