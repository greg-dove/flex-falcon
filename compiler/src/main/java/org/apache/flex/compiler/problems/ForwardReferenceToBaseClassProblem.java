/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.flex.compiler.problems;

import org.apache.flex.compiler.tree.as.IASNode;

/**
 * Diagnostic emitted when a class extends a superclass which is declared later
 * in a file, such as when the following is in a single file
 * 
 * <pre>
 * class A extends B
 * {
 * }
 * 
 * class B
 * {
 * }
 * 
 * </pre>
 */
public final class ForwardReferenceToBaseClassProblem extends CodegenProblem
{
    public static final String DESCRIPTION = "Forward reference to base class ${baseClassName}.";

    public static final int errorCode = 1458;
    public ForwardReferenceToBaseClassProblem(IASNode site, String baseClassName)
    {
        super(site);
        this.baseClassName = baseClassName;
    }

    public final String baseClassName;
}
