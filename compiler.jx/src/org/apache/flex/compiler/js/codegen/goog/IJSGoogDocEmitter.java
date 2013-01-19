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

package org.apache.flex.compiler.js.codegen.goog;

import org.apache.flex.compiler.definitions.IClassDefinition;
import org.apache.flex.compiler.definitions.ITypeDefinition;
import org.apache.flex.compiler.js.codegen.IJSDocEmitter;
import org.apache.flex.compiler.projects.ICompilerProject;
import org.apache.flex.compiler.tree.as.IASNode;
import org.apache.flex.compiler.tree.as.IClassNode;
import org.apache.flex.compiler.tree.as.IFunctionNode;
import org.apache.flex.compiler.tree.as.IParameterNode;
import org.apache.flex.compiler.tree.as.IVariableNode;

/**
 * The {@link IJSGoogDocEmitter} interface allows the abstraction of JavaScript
 * document comments to be emitted per tag.
 * <p>
 * The purpose of the API is to clamp emitted output to JavaScript doc tags. The
 * output can be multiline but is specific to one tag. This allows a full
 * comment to be created without worrying about how to assemble the tags.
 * <p>
 * The current tags were found at
 * https://developers.google.com/closure/compiler/docs/js-for-compiler#types
 * <p>
 * TODO (mschmalle) Might make a comment API and tag API so comments are not
 * dependent on tag creation IE IJSDocEmitter and IJSDocTagEmitter
 * 
 * @author Michael Schmalle
 */
public interface IJSGoogDocEmitter extends IJSDocEmitter
{
	
    void emitFieldDoc(IVariableNode node);

    void emitMethodDoc(IFunctionNode node, ICompilerProject project);

    void emitVarDoc(IVariableNode node);
    
    /*
     * https://developers.google.com/closure/compiler/docs/js-for-compiler#types
     *- @const - Marks a variable as read-only. The compiler can inline @const variables
     *
     *- @define - Indicates a constant that can be overridden by the compiler at compile-time.
     *
     * @deprecated - Warns against using the marked function, method, or property should not be used.
     * 
     *- @enum - Specifies the type of an enum. An enum is an object whose properties constitute a 
     *        set of related constants. The @enum tag must be followed by a type expression. 
     *        
     * @expose - Declares an exposed property. Exposed properties will not be removed, or renamed, 
     *         or collapsed, or optimized in any way by the compiler.
     *         
     *- @extends - Marks a class or interface as inheriting from another class. A class marked 
     *           with @extends must also be marked with either @constructor or @interface. 
     *           
     *- @implements - Used with @constructor to indicate that a class implements an interface. 
     *
     *- @inheritDoc - tag implies the @override tag.  has exactly the same documentation.
     *
     * @interface - Marks a function as an interface.
     * 
     * @lends
     * 
     * @license|@preserve - Tells the compiler to insert the associated comment before the compiled
     *                      code for the marked file.
     *                      
     * @nosideeffects - Indicates that a call to the declared function has no side effects
     * 
     *- @override - Indicates that a method or property of a subclass intentionally hides a method or 
     *              property of the superclass.
     *              
     * @param - Used with method, function and constructor definitions to specify the types of function 
     *          arguments. 
     *          
     * @private - Marks a member as private. Only code in the same file can access global variables and 
     *            functions marked @private. Constructors marked @private can only be instantiated by code 
     *            in the same file and by their static and instance members. 
     *            
     * @protected - Indicates that a member or property is protected.
     * 
     * @return - Specifies the return types of method and function definitions. The @return tag must be 
     *           followed by a type expression. 
     *           
     * @this - Specifies the type of the object to which the keyword this refers within a function. 
     *         The @this tag must be followed by a type expression. 
     *         
     * @type - Identifies the type of a variable, property, or expression. The @type tag must be 
     *         followed by a type expression. 
     *         
     * @typedef - Declares an alias for a more complex type. 
     */

    void emitConst(IVariableNode node);

    void emitDefine(IVariableNode node);

    void emitDeprecated(IASNode node);

    void emitEnum(IClassNode node);

    void emitExtends(IClassDefinition superDefinition);

    void emitImplements(ITypeDefinition definition);

    void emitInheritDoc(IClassNode node);

    void emitLicense(IClassNode node);

    void emitOverride(IFunctionNode node);

    void emitParam(IParameterNode node);

    void emitPrivate(IASNode node);

    void emitProtected(IASNode node);

    void emitReturn(IFunctionNode node);

    void emitThis(ITypeDefinition node);

    void emitType(IASNode node);

    void emitTypedef(IASNode node);
}